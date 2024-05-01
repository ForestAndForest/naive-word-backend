package com.example.naive.constant;

import lombok.Getter;

@Getter
public enum CustomExceptionEnum {
    USER_NOT_LOGIN("用户未登录", 1001),
    USER_NOT_EXIST("用户不存在", 1002),
    USER_EXIST("用户已存在", 1003),
    USER_PASSWORD_ERROR("用户密码错误", 1004),
    USER_NOT_ENABLE("用户未启用", 1005),
    USER_SIGNUP("用户注册失败", 1006),
    USER_LOGIN("用户登录失败", 1007),
    USER_LOGOUT("用户退出失败", 1008),
    USER_BANED("用户被封禁", 1009),
    USER_NOT_PERMISSIONS("用户没有权限", 4001),


    IO_ERROR("IO异常", 5001);

    private final String msg;
    private final int code;

    CustomExceptionEnum(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }
}
