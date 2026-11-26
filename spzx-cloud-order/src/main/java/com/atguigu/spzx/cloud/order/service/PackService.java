package com.atguigu.spzx.cloud.order.service;

import com.atguigu.spzx.cloud.order.vo.SePkgSplitVO;

import java.util.Date;
import java.util.List;

/**
 * @description: 分单信息
 * @author: apple
 * @date: 2026年11月26日 05:18
 * @version: 1.0
 */
public interface PackService {

    List<SePkgSplitVO> findSePkgList(Long orgid, String sTime,String eTime,Integer pageSize,Integer pageIndex);

}