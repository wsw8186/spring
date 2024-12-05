package com.example.ex01.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
@Aspect
@Component
@Slf4j
public class LoggingAdvice {
	
	//포인트컷 표현식
	//"execution(* com.example.ex01.domain.service.simpleServiceImpl.get1())"
	// execution : 메서드 실행
	// * 		 : 모든 리턴 타입
	// com.example.ex01.domain.service.simpleServiceImpl.get1() : 함수명
	
	@Before("execution(* com.example.ex01.domain.service.MemoServiceImpl.memoAddTx(..))")
	public void loggingBefore(JoinPoint joinPoint) {
		log.info("[AOP] BEFORE..." + joinPoint);
	}
	@After("execution(* com.example.ex01.domain.service.MemoServiceImpl.getMemos())")
	public void loggingAfter_2(JoinPoint joinPoint) {
		log.info("[AOP] AFTER..." + joinPoint);
		log.info("[AOP] AFTER..." + joinPoint.getTarget());
		log.info("[AOP] AFTER..." + joinPoint.getSignature());
		log.info("[AOP] AFTER..." + joinPoint.getSignature().getName());
	}
	@Around("execution(* com.example.ex01.domain.service.*.*(..))")
	public Object loggingAround_3(ProceedingJoinPoint pjp) throws Throwable {
		
		//이전시점
		log.info("[AOP] AROUND BEFORE");
		long startTime = System.currentTimeMillis();
		//MVC흐름대로 진행
		Object isUpdated =(boolean)pjp.proceed();
		//이후시점
		log.info("[AOP] AROUND AFTER");
		long endTime = System.currentTimeMillis();
		log.info("[AOP] TIME : " +(endTime+startTime) + " ms");
		
		return isUpdated;
		
	}
}







