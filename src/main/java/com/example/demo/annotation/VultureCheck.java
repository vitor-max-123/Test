package com.example.demo.annotation;

import java.lang.annotation.*;

/**
 * @author Administrator
 * @create 2021/10/26 0026 15:44
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface VultureCheck {
    String value() default "";
}
