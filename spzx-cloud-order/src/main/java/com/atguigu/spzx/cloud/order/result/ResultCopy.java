/**
 * @ClassName ResultCopy
 * @Description TODO
 * @Author apple
 * @Date 2026/11/25 16:09
 */
package com.atguigu.spzx.cloud.order.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultCopy<T> implements Serializable {

    private Integer code;

    private String message;

    private T data;

//    私有化构造方法 防止外部创建对象 静态工厂方法模式
    private ResultCopy(){}

//    创建静态方法

    // 成功 有返回信息
    public static <T> ResultCopy<T> success(T data){

        ResultCopy<T> result = new ResultCopy<>();

        result.setCode(200);

        result.setMessage("操作成功！");

        result.setData(data);

        return result;
    }

    // 成功 自定义返回信息
    public static <T> ResultCopy<T> success(String message,T data){

        ResultCopy result = new ResultCopy();

        result.setCode(200);

        result.setMessage(message);

        result.setData(data);

        return result;

    }

    public static<T> ResultCopy<T> error(String message){

        ResultCopy result = new ResultCopy();

        result.setCode(500);

        result.setMessage(message);

        return result;

    }


}
