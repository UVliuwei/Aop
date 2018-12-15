package com.uv.aop.config;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/*
 * @author liuwei
 * @date 2018/12/10 18:43
 *
 */
@Aspect
@Component
public class ServiceAspect {

//    @Pointcut("execution(public * com.uv.aop.service.*.*(..))")
    @Pointcut("@annotation(com.uv.aop.config.PrintTime)")
    public void serviceCut() {
    }

    @Before("serviceCut()")
    public void beforeCut(JoinPoint joinPoint) {
        System.out.println("before");
    }

    @After("serviceCut()")
    public void afterCut(JoinPoint joinPoint) {
        System.out.println("after");
    }

    @Around("serviceCut()")
    public void around(ProceedingJoinPoint pjp) {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            PrintWriter writer = response.getWriter();
            Object[] args = pjp.getArgs();
            if("1".equals((String) args[0])) {
                writer.write("Sam");
                writer.flush();
                return;
            }
            pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("around");
    }
    @AfterReturning(pointcut = "serviceCut()", returning = "returnVal")
    public void afterReturn(JoinPoint joinPoint, Object returnVal) {
        System.out.println("return value:" + returnVal);
    }

    @AfterThrowing(pointcut = "serviceCut()", throwing = "error")
    public void afterThrowing(JoinPoint joinPoint, Throwable error) {
        System.out.println("error " + error);
        System.out.println("afterThrowing");
    }

}
