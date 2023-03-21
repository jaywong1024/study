package top.hanjie.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopWebAspect {

    @Pointcut("execution(* top.hanjie.web.AopWeb.test(..))")
    public void myMethodPointcut() { }

    @Before("myMethodPointcut()")
    public void beforeMyMethod() {
        System.out.println("top.hanjie.web.AopWeb.test 执行前...");
    }

    @After("myMethodPointcut()")
    public void afterMyMethod() {
        System.out.println("top.hanjie.web.AopWeb.test 执行后...");
    }

    @AfterReturning(pointcut = "myMethodPointcut()", returning = "result")
    public void afterReturningMyMethod(Object result) {
        System.out.println("top.hanjie.web.AopWeb.test 返回结果后：" + result);
    }

    @AfterThrowing(pointcut = "myMethodPointcut()", throwing = "exception")
    public void afterThrowingMyMethod(Exception exception) {
        System.out.println("方法抛出异常：" + exception.getMessage());
    }

    @Around("myMethodPointcut()")
    public Object aroundMyMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("top.hanjie.web.AopWeb.test 环绕-前...");
        Object result = joinPoint.proceed();
        System.out.println("top.hanjie.web.AopWeb.test 环绕-后：" + result);
        return result;
    }

}
