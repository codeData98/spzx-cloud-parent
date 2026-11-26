package com.atguigu.test;

import java.time.ZonedDateTime;

public class DatetimeTest {
    public static void main(String[] args) {
        //获取当前时区时间代码
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);
        // 2025-09-16T15:41:22.096445600+08:00[Asia/Shanghai]
    }
}
