package com.nanGuoMM.reggie.front.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code;//响应码，1 代表成功; 0 代表失败
    private String msg;  //响应信息 描述字符串
    private T data; //返回的数据

    //增删改 成功响应
    public static <T> Result<T> success() {
        return new <T>Result<T>(1, "success", null);
    }

    //查询 成功响应
    public static <T> Result<T> success(T data) {
        return new <T>Result<T>(1, "success", data);
    }

    //失败响应
    public static <T> Result<T> error(String msg) {
        return new <T>Result<T>(0, msg, null);
    }
}
