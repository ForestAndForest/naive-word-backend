package com.example.naive.core;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private int code;
    private String msg;
    private Object data;
    private Long time;


    public static Result success(Object data) {
        return new Result(100, "操作成功", data,System.currentTimeMillis());
    }

    public static Result fail(int code, String msg) {
        return new Result(code, msg, null,System.currentTimeMillis());
    }
}
