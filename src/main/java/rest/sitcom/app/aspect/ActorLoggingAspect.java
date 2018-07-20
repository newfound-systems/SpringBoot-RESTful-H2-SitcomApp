/**
 * Copyright NEWFOUND SYSTEMS to Present
 * All Rights Reserved
 */
package rest.sitcom.app.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ActorLoggingAspect {
	private static final Logger logger = LogManager.getLogger(ActorLoggingAspect.class);

	/**
	 * Before Request in SitcomController
	 * 
	 * @param joinPoint
	 */
	@Before("execution(* rest.sitcom.app.controllers.ActorController.*(..))")
	public void beforeSitcomController(JoinPoint joinPoint) {
		logger.info("Signature: " + joinPoint.getSignature().getName());
		for (Object obj : joinPoint.getArgs()) {
			logger.info("Arg: " + obj);
		}
	}
	
	/**
	 * Before any Method
	 * 
	 * @param joinPoint
	 */
	@Before("execution(* rest.sitcom.app.repo.interfaces.ActorRepository.*(..))")
	public void beforeAny(JoinPoint joinPoint) {
		logger.info("Signature: " + joinPoint.getSignature().getName());
		for (Object obj : joinPoint.getArgs()) {
			logger.info("Arg: " + obj);
		}
	}

	/**
	 * After any Method
	 * 
	 * @param joinPoint
	 */
	@After("execution(* rest.sitcom.app.repo.interfaces.ActorRepository.*(..))")
	public void afterAny(JoinPoint joinPoint) {
		logger.info("Signature: " + joinPoint.getSignature().getName());
		logger.info("after execution of {}", joinPoint);
	}

	/**
	 * Successful Only
	 * 
	 * @param joinPoint
	 * @param result
	 */
	@AfterReturning(pointcut = "execution(* rest.sitcom.app.repo.interfaces.ActorRepository.*(..))", returning = "result")
	public void afterReturningAny(JoinPoint joinPoint, Object result) {
		logger.info("Signature: " + joinPoint.getSignature().getName());
		logger.info("{} returned with value {}", joinPoint, result);
	}

	/**
	 * after Throwing Exception on Any Method
	 * 
	 * @param joinPoint
	 * @param error
	 */
	@AfterThrowing(pointcut = "execution(* rest.sitcom.app.repo.interfaces.ActorRepository.*(..))", throwing = "error")
	public void afterThrowingAny(JoinPoint joinPoint, Throwable error) {
		logger.info("Signature: " + joinPoint.getSignature().getName());
		logger.info("after throw of {}", error.getMessage());
	}

	/**
	 * around Any Method
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* rest.sitcom.app.repo.interfaces.ActorRepository.*(..))")
	public Object aroundAny(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("Signature: " + joinPoint.getSignature().getName());
		long startTime = System.currentTimeMillis();

		Object proceed = joinPoint.proceed();

		long executionTime = System.currentTimeMillis() - startTime;
		logger.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");

		return proceed;
	}
	
	/**
	 * Around on findActorByFirstName
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* rest.sitcom.app.repo.interfaces.ActorRepository.*(..))")
	public Object aroundFindActorByFirstName(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("Signature: " + joinPoint.getSignature().getName());
		long startTime = System.currentTimeMillis();

		Object proceed = joinPoint.proceed();

		long executionTime = System.currentTimeMillis() - startTime;
		logger.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");

		return proceed;
	}
}