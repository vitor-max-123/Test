package com.example.demo.config;

import com.example.demo.annotation.VultureCheck;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Administrator
 * @create 2021/10/26 0026 15:47
 */
@Aspect
@Component
public class VultureAspectConfig {
    @Pointcut("@annotation(com.example.demo.annotation.VultureCheck)")
    public void pointCut(){}

    @Before(value = "pointCut()")
    public void checked(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature= (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        VultureCheck annotation = method.getAnnotation(VultureCheck.class);
        String value = annotation.value();
        Object[] args = joinPoint.getArgs();
        Long id=null;
        for (Object o:args){
            if (o instanceof Long){
                id= (Long) o;
            }
        }
        System.out.println(id);
        try {
            throw new Exception("asa");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
