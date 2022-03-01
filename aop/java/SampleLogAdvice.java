package sbc.addr.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Log4j
@Aspect
@Component // 기능 덩어리 ( Component, Module, Bean )
public class SampleLogAdvice {  // Aspec 을 지시해주는 클래스
	
	@Before("execution(* sbc.addr.service.SampleLogService*.*(..))") // 메소드를 실행하기 전
	public void logBefore() {
		log.info("#(3) logBefore() 수행");
	}
	@Around("execution(* sbc.addr.service.SampleLogService*.*(..))") // 메소드를 실행하기 전과 후
	public Object logTime(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		
		log.info("#(1) target class: " + pjp.getTarget().getClass());
		log.info("#(2) args: " + Arrays.deepToString(pjp.getArgs()));
		
		Object result = null;
		try {
			result = pjp.proceed();  // 비지니스 메소드로 진행하도록 하는 메소드
		}catch(Throwable t) {
			log.info("#예외발생 t: " + t);
		}
		
		long end = System.currentTimeMillis();
		log.info("#(4)수행시간: " + (end - start));
		return result;
	}
	@After("execution(* sbc.addr.service.SampleLogService*.*(..))")
	public void logAfter() {
		log.info("#(5) logAfter() 수행");
	}
	// 파라미터가 두개일때는 pointcut 을 생략할 수 없다.
	@AfterThrowing(pointcut = "execution(* sbc.addr.service.SampleLogService*.*(..))", throwing="exception")
	public void logAfterThrowsing(Exception exception) {
		log.info("#(5) logAfterThrowsing() exception: " + exception);
	}
}
