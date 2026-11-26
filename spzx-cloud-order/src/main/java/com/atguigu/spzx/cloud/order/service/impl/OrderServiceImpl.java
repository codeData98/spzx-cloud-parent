package com.atguigu.spzx.cloud.order.service.impl;

import com.atguigu.spzx.cloud.order.entity.Order;
import com.atguigu.spzx.cloud.order.entity.User;
import com.atguigu.spzx.cloud.order.mapper.OrderMapper;
import com.atguigu.spzx.cloud.order.service.OrderService;
import com.atguigu.spzx.cloud.order.service.feign.UserFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    RestTemplate restTemplate; //1.注入模板对象

    @Autowired
    UserFeignClient userFeignClient; //注入代理对象进行远程调用。

    //推荐：OpenFeign组件。面向接口编程的。
    @Override
    public Order findOrderByOrderId(Long orderId) {
        //  debug < info < warn < error
        log.debug("debug...");
        log.info("info....");
        log.warn("warn...");
        log.error("error");

        Order order = orderMapper.findOrderByOrderId(orderId);

        //返回Order订单对象的同时，需要返回它的对应用户信息
        //2.远程调用获取用户数据
        Long userId = order.getUserId(); //订单表关联用户的外键
        //存在两个问题：
        //   (1) 地址写死，硬代码编程。不利于维护。
        //   (2) 如果远程集群，无法负载均衡。
        User user = userFeignClient.findUserByUserId(userId);
        order.setUser(user); //封装关联数据
        return order; //订单和用户数据一起返回
    }

    //传统远程调用方式，可以用但不推荐。不面向接口编程。
/*    @Override
    public Order findOrderByOrderId(Long orderId) {
        Order order = orderMapper.findOrderByOrderId(orderId);

        //返回Order订单对象的同时，需要返回它的对应用户信息
        //2.远程调用获取用户数据
        Long userId = order.getUserId(); //订单表关联用户的外键
        //存在两个问题：
        //   (1) 地址写死，硬代码编程。不利于维护。
        //   (2) 如果远程集群，无法负载均衡。
        User user = restTemplate.getForObject("http://spzx-cloud-user/api/user/findUserByUserId/" + userId, User.class);

        order.setUser(user); //封装关联数据
        return order; //订单和用户数据一起返回
    }*/
}
