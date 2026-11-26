package com.atguigu.spzx.cloud.order.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SePkgSplitItemVO {

    // ===== 装箱明细：SE_PACK_M =====
    private Integer pkSeq;
    private Integer packStatus;
    private String  sizeRun;
    private BigDecimal ctnPairs;
    private BigDecimal nw;
    private BigDecimal gw;
    private String  itemNo;
    private Integer sNo;
    private Integer eNo;
    private BigDecimal length;
    private BigDecimal width;
    private BigDecimal high;

    // ===== 箱条码：SE_PACK_BARCODE =====
    private Integer ctnsNumber;
    private String  barcodeNo;

}
