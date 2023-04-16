package com.demo.proj.service;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InterfaceLimit {
    long time() default 1000; // 限制时间 单位：毫秒(默认值：一分钟）

    int value() default 100; // 允许请求的次数(默认值：5次）
}
