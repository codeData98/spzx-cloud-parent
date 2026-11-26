/**
 * @ClassName Result
 * @Description TODO
 * @Author apple
 * @Date 2026/11/25 15:48
 */
package com.atguigu.spzx.cloud.order.result;


import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {

    // 状态码（例如：200表示成功，500表示失败）
    private int code;
    // 提示信息
    private String message;
    // 具体返回的业务数据（使用泛型，支持返回任意类型）
    private T data;

    // 私有化构造方法，强制使用静态工厂方法
    private Result() {}

    /**
     * 成功返回（带数据）
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage("操作成功");
        result.setData(data);
        return result;
    }

    /**
     * 成功返回（不带数据）
     */
    public static <T> Result<T> success() {
        return success(null);
    }

    /**
     * 失败返回
     */
    public static <T> Result<T> fail(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}
