package org.cbs.aop;



import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
@Component
public class LogAdvice {
	
	/**
	 * AOP를 활용해 비즈니스로직의 파라미터와 메서드 수행시간 로그로 찍기 
	 * @Around : joinpoint 메서드의 실행 시점을 내가 지정하고싶을 떄 사용, Advice가 joinPoint를 둘러 쌓으므로 @Around
	 *   1) Object로 반환 한 이유 : @Around 적용시에 리턴타입이 void면 안되고 joinPoint메서드 실행결과도 반드시 반환해 줘야함                    
	 * @param proceedingJoinPoint : Advice가 적용된 메서드(JoinPoint)를 제어하는 객체
	 *          1) .getTarget() : AOP 타겟 객체 파악 
	 *          2) .getArgs() : JoinPoint의 파라미터 파악가능
	 *          3) .proceed() : JoinPoint 메서드를 실행
	 * @return  
	 */
	@Around("execution(* org.cbs.service.BoardService*.*(..))")
	public Object logTime(ProceedingJoinPoint proceedingJoinPoint) {
		
		long start = System.currentTimeMillis();
		
		log.info("Target: " + proceedingJoinPoint.getTarget());
		log.info("Param: " + Arrays.toString(proceedingJoinPoint.getArgs()));
		
		//invoke method
		Object result = null;
		
		try {
			result = proceedingJoinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		
		log.info("TIME: " + (end - start));
		
		return result;
	}
}
