package com.example.naive.exception;

import com.example.naive.annotation.MyException;
import com.example.naive.constant.CustomExceptionEnum;

@MyException(CustomExceptionEnum.USER_NOT_LOGIN)
public class UserNotLoginException extends RuntimeException {
}
