package org.goldstine.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * AOP使用场景：
 *      （1）AOP加日志保存到数据库中
 *      （2）AOP做权限认证 ，Filter能做的AOP都能做
 *      （3）AOP做安全检查
 *      （4）AOP做事务控制
 */

@Aspect
@Component
public class BValidateAspect {
    @Before("org.goldstine.utils.LogUtils.hahaMyPoint()")//通过全类名引用外部类的可重用切入点表达式
    public static void logStart(JoinPoint joinPoint){

        //获取目标方法的详细信息
        Object[] args = joinPoint.getArgs();
        //获取目标方法的签名对象，签名对象包含目标方法的详细信息
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();

        System.out.println("【Validate-前置通知】["+name+"]方法开始执行，用的参数列表为["+ Arrays.toString(args)+"]");
    }

    @AfterReturning(value = "org.goldstine.utils.LogUtils.hahaMyPoint()",returning = "result")
    public static void logReturn(JoinPoint joinPoint,Object result){

        //获取目标方法的执行返回值
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();

        System.out.println("【Validate-返回通知】["+name+"]方法结束执行，返回值为"+result);
    }

    @AfterThrowing(value = "org.goldstine.utils.LogUtils.hahaMyPoint()",throwing = "exception")
    public static void logException(JoinPoint joinPoint,Exception exception){
        System.out.println("【Validate-异常通知】["+joinPoint.getSignature().getName()+"]方法出现异常，"+exception);
    }

    @After("org.goldstine.utils.LogUtils.hahaMyPoint()")
    public int logEnd(JoinPoint joinPoint){
        System.out.println("【Validate-后置通知】["+joinPoint.getSignature().getName()+"]方法最终结束了。。。。");
        return 0;
    }
}
