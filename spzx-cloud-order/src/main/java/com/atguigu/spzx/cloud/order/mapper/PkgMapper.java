package com.atguigu.spzx.cloud.order.mapper;

import com.atguigu.spzx.cloud.order.vo.SePkgSplitItemVO;
import com.atguigu.spzx.cloud.order.vo.SePkgSplitVO;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @description: 分单Mapper
 * @author: apple
 * @date: 2026年11月26日 05:27
 * @version: 1.0
 */
@Mapper
public interface PkgMapper {

    List<SePkgSplitVO> findSePkgList(@Param("orgId") Long orgId,
                                     @Param("sTime") String sTime,
                                     @Param("eTime") String eTime,
                                     @Param("offset") Integer offset,
                                     @Param("size") Integer size);

    List<SePkgSplitItemVO> findSePkgIiemList(@Param("pkgNos") List<String> pkgNos);
}