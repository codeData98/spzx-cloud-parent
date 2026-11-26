package com.atguigu.spzx.cloud.order.controller;

import com.atguigu.spzx.cloud.order.entity.Order;
import com.atguigu.spzx.cloud.order.properties.PatternProperties;
import com.atguigu.spzx.cloud.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(value = "/api/order")
//@RefreshScope //自动刷新，配置中心修改配置，自动识别。
@EnableConfigurationProperties(PatternProperties.class) //让指定的类生效。
public class OrderController {

    @Autowired
    private OrderService orderService ;

    //@Value("${pattern.dateformat}") //拉取配置   不需要提供geter/seter   默认不支持热更新。需要配合@RefreshScope注解。
    //String pattern;

    @Autowired
    PatternProperties patternProperties;

    @GetMapping(value = "/findOrderByOrderId/{orderId}")
    public Order findOrderByOrderId(@PathVariable("orderId") Long orderId) {

/*        System.out.println("pattern = " + pattern);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String datetimeStr = sdf.format(new Date());
        System.out.println("datetimeStr = " + datetimeStr);*/

        System.out.println("patternProperties.getDateformat() = " + patternProperties.getDateformat());

        return orderService.findOrderByOrderId(orderId) ;
    }

}
