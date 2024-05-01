package com.example.naive.exception;

import com.example.naive.annotation.MyException;
import com.example.naive.constant.CustomExceptionEnum;

/**
 * @author 29002
 */
@MyException(CustomExceptionEnum.USER_NOT_PERMISSIONS)
public class UserNotPermissionException extends RuntimeException{
}
