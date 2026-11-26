package com.atguigu.spzx.cloud.order.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//@NoArgsConstructor
//@AllArgsConstructor
@Data
//@Component
@ConfigurationProperties(prefix = "pattern") //指定属性前缀，批量拉取配置。  默认支持热更新。
public class PatternProperties {
    private String dateformat;
}
