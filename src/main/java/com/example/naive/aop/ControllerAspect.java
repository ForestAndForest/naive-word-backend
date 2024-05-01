package com.example.naive.aop;


import com.example.naive.annotation.MyException;
import com.example.naive.core.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerAspect {


    @Pointcut("@within(com.example.naive.annotation.MyResponseBody)")
    private void myResponseBody() {
    }


    @Around(value = "myResponseBody()")
    private Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return Result.success(joinPoint.proceed());
        } catch (Exception e) {
            String msg = e.getMessage();
            int code = 505;
            if (e.getClass().isAnnotationPresent(MyException.class)) {
                MyException myException = e.getClass().getAnnotation(MyException.class);
                msg = myException.value().getMsg();
                code = myException.value().getCode();
            }
            return Result.fail(code, msg);
        }
    }
}
