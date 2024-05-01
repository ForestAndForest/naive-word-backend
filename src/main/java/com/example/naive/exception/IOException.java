package com.example.naive.exception;

import com.example.naive.annotation.MyException;
import com.example.naive.constant.CustomExceptionEnum;

@MyException(CustomExceptionEnum.IO_ERROR)
public class IOException extends RuntimeException{
}
