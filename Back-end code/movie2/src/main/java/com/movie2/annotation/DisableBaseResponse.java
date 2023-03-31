package com.movie2.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//有这个注解，代表数据不封装（字符串，图片验证码）
@Target(ElementType.METHOD)//该注解注解在方法上
@Retention(RetentionPolicy.RUNTIME)//程序运行时该注解还是存在的
public @interface DisableBaseResponse {

}