package com.aspectj.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.aspectj.model.Student;

@Aspect
@Component
public class GeneralInterceptorAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(GeneralInterceptorAspect.class);
	
	@Pointcut("execution(* com.aspectj.controller.*.*(..))")
	public void logginPoinCut() {}
	
	@Before("logginPoinCut()")
	public void before(JoinPoint joinPoint) {
		logger.info("Before method invoked::"+joinPoint.getSignature());
	}
	
	@After("logginPoinCut()")
	public void after(JoinPoint joinPoint) {
		logger.info("After method invoked::"+joinPoint.getSignature());
	}
	
	@AfterReturning(value= "execution(* com.aspectj.controller.*.*(..))", returning = "student")
	public void after(JoinPoint joinPoint, Student student) {
		logger.info("After method invoked::"+student);
	}
	
	@AfterThrowing(value= "execution(* com.aspectj.controller.*.*(..))", throwing = "e")
	public void after(JoinPoint joinPoint, Exception e) {
		logger.info("After method invoked::"+e);
	}

}
