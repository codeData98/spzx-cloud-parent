package com.atguigu.spzx.cloud.order.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
//@SpringBootConfiguration 代替了@Configuration

//声明负载均衡器使用：调用指定的微服务，采用指定的算法
//@LoadBalancerClients(
//        {
//                @LoadBalancerClient(
//                        name = "spzx-cloud-user",
//                        configuration = CustomLoadBalancerConfiguration.class
//                )
//        }
//)
public class RestTemplateConfiguration {

    //用于远程调用的工具类对象
    @Bean
    @LoadBalanced //RestTemplate只能远程调用，要想负载均衡，可以集成LoadBalancer组件。默认策略：轮询
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
