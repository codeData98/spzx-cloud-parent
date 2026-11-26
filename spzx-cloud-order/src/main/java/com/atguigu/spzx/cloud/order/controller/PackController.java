package com.atguigu.spzx.cloud.order.controller;

import com.atguigu.spzx.cloud.order.dto.PackQueryDTO;
import com.atguigu.spzx.cloud.order.result.Result;
import com.atguigu.spzx.cloud.order.service.PackService;
import com.atguigu.spzx.cloud.order.vo.SePkgSplitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pack")
public class PackController {

    @Autowired
    private PackService packService;

    @PostMapping("packList")
    public Result getPkgByOrgAndTime(@RequestBody PackQueryDTO packQueryDTO){

        if(packQueryDTO.getOrgId() == null || packQueryDTO.getStartTime() == null || packQueryDTO.getEndTime() == null){
            return Result.fail(500,"厂商/截止时间不能为空，请检查！");
        }

        if(packQueryDTO.getPageSize() == null || packQueryDTO.getPageIndex() == null){
            return Result.fail(500,"分页信息为空，请检查");
        }

        List<SePkgSplitVO> voList = packService.findSePkgList(packQueryDTO.getOrgId(),packQueryDTO.getStartTime(),packQueryDTO.getEndTime(),packQueryDTO.getPageSize(),packQueryDTO.getPageIndex());

        return Result.success(voList);

        //return
    }

}
