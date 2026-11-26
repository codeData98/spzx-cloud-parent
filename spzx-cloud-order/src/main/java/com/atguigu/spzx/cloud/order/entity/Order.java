package com.atguigu.spzx.cloud.order.entity;

import lombok.Data;


import java.math.BigDecimal;

@Data
public class Order {

    private Long id ;
    private Long userId ; //手动映射
    private String name ;
    private BigDecimal price ;
    private Integer num ;


    private User user; // 关联对象   订单和用户是1对1的关联关系

}
