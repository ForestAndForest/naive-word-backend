package com.example.naive.annotation;

import com.example.naive.constant.CustomExceptionEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyException {
    CustomExceptionEnum value();
}
