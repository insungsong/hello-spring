package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {
    @Around("execution(* hello.hellospring..*(..))")
    public Object execut(ProceedingJoinPoint joinPoint) throws Throwable {
        long start =  System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try{
            Object result =  joinPoint.proceed();
            return result;
        }finally {
            long finsish = System.currentTimeMillis();
            long timeMs = finsish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
