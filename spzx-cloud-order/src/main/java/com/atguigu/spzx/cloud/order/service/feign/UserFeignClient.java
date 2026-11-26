package com.atguigu.spzx.cloud.order.service.feign;

import com.atguigu.spzx.cloud.order.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//Feign接口：用于服务间调用。
@FeignClient(name="spzx-cloud-user") //指定被远程调用的微服务名称
public interface UserFeignClient {

    //这个方法声明相当于一个配置：
    //告诉OpenFeign组件重新构建请求时：
    // 1.调用哪个地址接口
    // 2.调用方法method="get"
    // 3.@PathVariable不能省略value属性。将参数通过占位符传递
    // 4.远程接口返回什么类型数据
    @GetMapping("/api/user/findUserByUserId/{id}") // 注意路径完整性
    public User findUserByUserId(@PathVariable("id") Long id);
}
