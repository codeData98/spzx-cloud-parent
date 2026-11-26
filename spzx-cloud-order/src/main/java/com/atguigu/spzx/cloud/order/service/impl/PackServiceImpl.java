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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PackServiceImpl implements PackService {
    private final PkgMapper pkgMapper;

    public PackServiceImpl(PkgMapper pkgMapper) {
        this.pkgMapper = pkgMapper;
    }

    @Override
    public List<SePkgSplitVO> findSePkgList(Long orgid, String sTime, String eTime,Integer pageSize,Integer pageIndex) {

        List<SePkgSplitVO> voList = pkgMapper.findSePkgList(orgid,sTime,eTime,pageSize,pageIndex);

//        List<String> pkgNos = new ArrayList<>();
//
//        for(SePkgSplitVO vo : voList){
//
//            pkgNos.add(vo.getPkgNo());
//
//        }

        List<String> pkgNos = voList.stream().filter(vo -> vo.getPkgStatus() != null).map(SePkgSplitVO::getPkgNo).collect(Collectors.toList());

        // 获取分单的装箱信息
        List<SePkgSplitItemVO> sePkgIiemList = pkgMapper.findSePkgIiemList(pkgNos);

        // 拼装信息
        

        return voList;
    }
}
