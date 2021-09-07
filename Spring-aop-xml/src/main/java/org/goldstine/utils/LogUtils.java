package org.goldstine.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

import java.util.Arrays;

public class LogUtils {


    public Object myAround(ProceedingJoinPoint joinPoint)  {
        //通过joinPoint获得对应的执行参数
        Object[] args = joinPoint.getArgs();
        //获取目标方法的详细信息
        String name = joinPoint.getSignature().getName();//可以获得目标方法的方法名
        //就是利用反射调用目标方法即可，就是method.invoke(obj,args)
        Object proceed=null;
        try {
            System.out.println("环绕哦前置通知....."+"["+name+"]"+"目标方法开始执行....");
            proceed = joinPoint.proceed(args);//通过反射调用目标方法进行执行
            System.out.println("环绕哦返回通知....."+"["+name+"]"+"方法返回值为:"+proceed);
        } catch (Exception exception) {
            System.out.println("环绕哦异常通知....."+"["+name+"]"+"异常信息:"+exception);
            exception.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            System.out.println("环绕哦后置通知....."+"["+name+"]方法结束");
        }

        return proceed;
    }


    public static void logStart(JoinPoint joinPoint){

        //获取目标方法的详细信息
        Object[] args = joinPoint.getArgs();
        //获取目标方法的签名对象，签名对象包含目标方法的详细信息
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();

        System.out.println("【LogUtils-前置通知】["+name+"]方法开始执行，用的参数列表为["+Arrays.toString(args)+"]");
    }


    public static void logReturn(JoinPoint joinPoint,Object result){

        //获取目标方法的执行返回值
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();

        System.out.println("【LogUtils-返回通知】["+name+"]方法结束执行，返回值为"+result);
    }


    public static void logException(JoinPoint joinPoint,Exception exception){
        System.out.println("【LogUtils-异常通知】["+joinPoint.getSignature().getName()+"]方法出现异常，"+exception);
    }


    public int logEnd(JoinPoint joinPoint){
        System.out.println("【LogUtils-后置通知】["+joinPoint.getSignature().getName()+"]方法最终结束了。。。。");
        return 0;
    }

}
