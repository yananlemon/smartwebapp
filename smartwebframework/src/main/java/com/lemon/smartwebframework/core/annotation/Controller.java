package com.lemon.smartwebframework.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 控制器注解
 * @author yanan
 *
 */
@Target(ElementType.TYPE)//ElementType.TYPE:用于描述类、接口(包括注解类型) 或enum声明
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {

	String value() default "";// 请求路径
}
