package ru.innopolis.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Around("execution(public * ru.innopolis.*.*.*(..))")
    public Object loggingPublicMethodCall(ProceedingJoinPoint pjp) throws Throwable {
        // логирует вызов любого публичного метода
        var methodName = pjp.getSignature().toLongString();
        log.info("Вызов метода: {} c параметрами: {}", methodName, pjp.getArgs());
        return pjp.proceed();
    }

}
