package com.atguigu.spzx.cloud.user.controller;

import com.atguigu.spzx.cloud.user.entity.User;
import com.atguigu.spzx.cloud.user.service.UserService;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Value("${server.port}")
    String port;

    //远程接口：被订单微服务调用
    @GetMapping("findUserByUserId/{id}")
    public User findUserByUserId(@PathVariable("id") Long id,
                                 @RequestHeader(value="company",required = false,defaultValue = "BAT") String company,
                                 @RequestParam(value="email",required = false,defaultValue = "xx@163.com") String email) {
        System.out.println("company = " + company);
        System.out.println("email = " + email);
        //System.out.println("user服务的findUserByUserId接口被调用了....");
        /*try {
            TimeUnit.SECONDS.sleep(61);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        User user = userService.findUserByUserId(id);
        user.setUserName(user.getUserName()+" " + port);
        return user; //转换为json返回
    }

}
