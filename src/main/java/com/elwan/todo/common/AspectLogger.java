package com.elwan.todo.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
//@Component
public class AspectLogger {
	
	private static final AppLogger logger = new AppLogger(AspectLogger.class);
	
	@Before("execution(public * *(..))")  
	public void logBefore(JoinPoint jp) {
		logger.info("Start executing method [{}] ...", jp.getSignature().getName());
	}
	
	@After("execution(public * *(..))")
	public void logAfter(JoinPoint jp) {
		logger.info("Execution of method [{}] has been finished.", jp.getSignature().getName());
	}
	
	@AfterReturning(pointcut = "execution(public * *(..))", returning= "result")  
	public void logAfterReturning(JoinPoint jp, Object result) {
		logger.info("Result [{}] has been returned.", result.toString());
	}
	
	@AfterThrowing( pointcut = "execution(public * *(..))", throwing= "t")  
	public void logAfterThrowing(JoinPoint jp, Throwable t) {
		logger.info("Exception [{}] has been thrown!", t.getMessage());
	}
	
	@Around("execution(public * *(..))")
	public void logAround(JoinPoint jp) {
		logger.info("********************************************", jp.getSignature().getName());
	}

}
