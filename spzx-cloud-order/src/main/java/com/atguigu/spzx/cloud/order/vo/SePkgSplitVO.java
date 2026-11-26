package com.atguigu.spzx.cloud.order.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SePkgSplitVO {

    // ===== 分单头：SE_PKG_M =====
    private Integer orgId;
    private String  seId;
    private String  pkgNo;
    private String  outCountry;
    private String  addrNo;
    private BigDecimal pkgQty;
    private LocalDateTime chkDate;
    private Integer pkgStatus;      // SE_PKG_M.STATUS

    // ===== 分单明细：SE_PKG_D =====
    private Integer seSeq;
    private String  color;
    private String  artName;

    // ===== 订单：SE_ORD_ITEM / SE_ORD_M =====
    private Integer itemStatus;     // SE_ORD_ITEM.STATUS
    private String  po;
    private String  custNm;
    private String  creaUser;
    private String  prodNo;         // GG_2103.GF_SE_PROD 的结果

    private List<SePkgSplitItemVO> children;

}
