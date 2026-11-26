package com.atguigu.spzx.cloud.order.service;

import com.atguigu.spzx.cloud.order.entity.Order;

public interface OrderService {

    // 根据订单的id查询订单数据
    Order findOrderByOrderId(Long orderId) ;

}
