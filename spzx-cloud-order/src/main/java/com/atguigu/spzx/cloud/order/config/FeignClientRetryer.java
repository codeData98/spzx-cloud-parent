package com.atguigu.spzx.cloud.order.config;

import feign.RetryableException;
import feign.Retryer;

public class FeignClientRetryer implements Retryer {
    private int start = 1;
    private int end = 3;

    //首次1次 + 重试2次
    @Override
    public void continueOrPropagate(RetryableException e) {
        if(start>=end){
            throw new RuntimeException(e);
        }
        start++;
    }

    @Override
    public Retryer clone() {
        return new FeignClientRetryer();
    }
}
