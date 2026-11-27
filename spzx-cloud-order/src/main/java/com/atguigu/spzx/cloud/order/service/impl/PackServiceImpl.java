/**
 * @ClassName PackServiceImpl
 * @Description TODO
 * @Author apple
 * @Date 2026/11/26 05:22
 */
package com.atguigu.spzx.cloud.order.service.impl;

import com.atguigu.spzx.cloud.order.mapper.PkgMapper;
import com.atguigu.spzx.cloud.order.service.PackService;
import com.atguigu.spzx.cloud.order.vo.SePkgSplitItemVO;
import com.atguigu.spzx.cloud.order.vo.SePkgSplitVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PackServiceImpl implements PackService {
    private final PkgMapper pkgMapper;

    public PackServiceImpl(PkgMapper pkgMapper) {
        this.pkgMapper = pkgMapper;
    }

    @Override
    public List<SePkgSplitVO> findSePkgList(Long orgid, String sTime, String eTime, Integer pageSize, Integer pageIndex) {

        // 1. 查父行（分页）：offset = (页码 - 1) * 每页条数
        int offset = (pageIndex - 1) * pageSize;
        List<SePkgSplitVO> voList = pkgMapper.findSePkgList(orgid, sTime, eTime, offset, pageSize);

        // 没查到父行就直接返回，避免后面拼出 IN () 的非法 SQL
        if (CollectionUtils.isEmpty(voList)) {
            return voList;
        }

        // 2. 取本页的 pkgNo 集合，一次查出所有子行
        List<String> pkgNos = voList.stream()
                .map(SePkgSplitVO::getPkgNo)
                .distinct()
                .collect(Collectors.toList());
        List<SePkgSplitItemVO> itemList = pkgMapper.findSePkgIiemList(orgid, pkgNos);
        if (CollectionUtils.isEmpty(itemList)) {
            return voList;
        }

        // 3. 手动分组：key = pkgNo，value = 这张分单的所有子行
        Map<String,List<SePkgSplitItemVO>> test = new HashMap<>();
        for (SePkgSplitItemVO item : itemList){
            String pkgNo = item.getPkgNo();
            if (!test.containsKey(pkgNo)){
                test.put(pkgNo,new ArrayList<>());
            }
            test.get(pkgNo).add(item);
        }
        Map<String, List<SePkgSplitItemVO>> groupByPkgNo = new HashMap<>();
        for (SePkgSplitItemVO item : itemList) {
            String pkgNo = item.getPkgNo();
            // 第一次遇到这个 pkgNo，先创建一个空集合放进去
            if (!groupByPkgNo.containsKey(pkgNo)) {
                groupByPkgNo.put(pkgNo, new ArrayList<>());
            }
            // 取出这个 pkgNo 对应的集合，把当前子行追加进去
            groupByPkgNo.get(pkgNo).add(item);
        }

        // 4. 遍历父行，把对应的子行集合塞进去
        for (SePkgSplitVO vo : voList) {
            List<SePkgSplitItemVO> children = groupByPkgNo.get(vo.getPkgNo());
            if (children == null) {
                // 这张分单没有子行时，给个空集合，避免前端拿到 null
                children = new ArrayList<>();
            }
            vo.setChildren(children);
        }

        return voList;
    }
}
