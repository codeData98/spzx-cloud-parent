package com.atguigu.spzx.cloud.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 自定义全局过滤器：简单的权限校验
 */
@Component //生效：只需要纳入ioc容器即可。
public class AuthorizationFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("网关全局过滤器AuthorizationFilter进行【请求拦截】....");

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        String username = request.getQueryParams().getFirst("username");
        if(!StringUtils.hasText(username)){ //获取请求参数、请求头、请求cookie，简单做个权限校验
            response.setStatusCode(HttpStatus.NOT_ACCEPTABLE); // 406 不可接收的请求
            return response.setComplete(); //拒绝，不放行。
        }


        Mono<Void> mono = chain.filter(exchange);//放行

        System.out.println("网关全局过滤器AuthorizationFilter进行【响应拦截】....");
        return mono;
    }

    @Override
    public int getOrder() {
        return 1; //多个过滤器执行顺序，值越小优先级越高。 Integer.MIN_VALUE ~ Integer.MAX_VALUE
    }
}
