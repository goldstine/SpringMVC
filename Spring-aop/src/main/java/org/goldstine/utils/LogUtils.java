package org.goldstine.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 动态代理----》AOP
 * 如何将这个类的（切面类）中的这些方法（通知方法）动态的在目标方法的各个位置切入
 *
 * 步骤：
 * 首先导入对应的依赖：
 *  基础班的依赖  Aspects  spring-aspects
 *  使用第三方的加强版的包：即使目标对象没有实现任何接口也能创建动态代理
 *com.springsource.net.sf.cglib-2.2.0.jar
 * com.springsource.org.aopalliance-1.0.0.jar
 * com.springsource.org.aspectj.weaver-1.6.8.RELEASE.ja
 *
 * （2）通过配置类将目标类和切面类加入ioc容器中,除此之外，对于切面类还需要告诉ioc，通过注解@Aspect标记为切面类
 * 开启基于注解的AOP   在配置文件中加上<aop:aspectj-autoproxy></aop:aspectj-autoproxy>
 *(3)告诉springioc每一个切面类中的通知方法都是何时何地进行运行的
 *
 */

/**
 * 环绕切面只会影响对应的切面类，多切面时，环绕通知不会影响其他的切面类
 */

@Aspect
@Component   //将切面类加入ioc
@Order(1)//通过Order改变切面执行顺序，数值越小优先级越高
public class LogUtils {
    //日志开始之前进行传参
//    public static void logStart(Method method,Object... objects){
//        System.out.println("["+method.getName()+"]方法开始执行，用的参数列表为["+ Arrays.asList(objects)+"]");
//    }
//
//    public static void logReturn(Method method,Object result){
//        System.out.println("["+method.getName()+"]方法执行完成，计算结果是："+result);
//    }
//
//    public static void logException(Method method,Exception e){
//        System.out.println("["+method.getName()+"]方法执行出现异常了，异常信息是："+e.getCause()+";这个异常已经通知测试小组进行排除....");
//    }
//
//    public static void loeEnd(Method method){
//        System.out.println("["+method.getName()+"]方法最终之行结束了....");
//    }


    //抽取可重用的切入点表达式
    /**
     * (1)定义一个空方法
     * （2）将可重用的切入点表达式加上
     * （3）将需要切入的方法加上空方法的方法名(参数列表)
     *
     */
    @Pointcut("execution(public int org.goldstine.impl.MyMathCalculator.*(int,int))")
    public void hahaMyPoint(){}

    /**
     * 环绕通知，是spring中最强大的通知
     *  环绕通知==动态代理
     *  相当于四个注解合成一个   四合一
     *
     */

//    @Order(2)
    @Around("hahaMyPoint()")
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

//        System.out.println("环绕哈哈....");
        //将反射调用的返回值返回出去
        return proceed;
    }

    /**
     * 告诉spring每个方法都什么时候运行
     *
     * try {
     *   @Before
     *  result = method.invoke(calculator, args);
     *    @AfterReturning
     *  } catch (IllegalAccessException e) {
     *   @AfterThrowing
     * }finally {
     *        @After
     *    }
     *
     *
     *
     * @Before         在目标方法之前运行    前置通知
     * @AfterReturning  在目标方法正常返回之后    返回通知
     * @AfterThrowing   在目标方法抛出异常之后运行    异常通知
     *  @After        在目标方法结束之后   后置通知
     * @Around   环绕     环绕通知
     *
     * *:匹配一个或多个字符execution(public int org.goldstine.impl.MyMath*.*(int,int))
     *    对于重载的方法，可以用于匹配任意参数  匹配任意参数：execution(public int org.goldstine.impl.MyMathCalculator.*(int,*))
     *    ..匹配任意多个和任意类型的参数   execution(public int org.goldstine.impl.MyMathCalculator.*(..))
     *          匹配任意多层路径
     *    "execution(public int org.goldstine.impl.MyMathCalculator..*(int,*))"
     *
     *    权限位置不能使用*，但是权限位置可以不写
     *    最精确：
     *    最模糊：execution(* *.*(..))
     *
     *    切入点表达式：&& || ！
     *    execution(public int org.goldstine.impl.MyMathCalculator.*(int,*))&&execution(* *.*(int,int))表示需要同时满足两个表达式
     *
     *      通知方法的执行顺序：
     *          正常执行结束：@Before(前置通知)=====@After(后置通知)=====@AfterReturning(返回通知)
     *          异常执行结束：@Before(前置通知)====@After（后置通知）=====@AfterThrowing(异常通知)
     *
     *
     *          细节四：
     *              我们可以在通知方法的运行时候，拿到目标方法的详细信息
     *              只需要为通知方法的参数列表上加上一个参数JoinPoint joinPoint:封装了当前目标方法的详细信息
     *
     *          告诉Spring那个参数接收执行结果返回值
     *
     *          通知方法的唯一要求就是方法的参数列表一定不能乱写
     *              通知方法是Spring利用反射调用的，每次方法调用都需要确定这个方法的参数表的值
     *  Exception exception:通知方法的参数接收类型尽量写大一些，接收异常的参数类型为Exception   接收返回结果的参数类型为Object
     */



    //想在执行目标方法之前执行，写切入点表达式
    //execution(访问权限符  返回值类型 方法签名)
//    @Before("execution(public int org.goldstine.impl.MyMathCalculator.*(int,*))")
    @Before("hahaMyPoint()")
    public static void logStart(JoinPoint joinPoint){

        //获取目标方法的详细信息
        Object[] args = joinPoint.getArgs();
        //获取目标方法的签名对象，签名对象包含目标方法的详细信息
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();

        System.out.println("【LogUtils-前置通知】["+name+"]方法开始执行，用的参数列表为["+Arrays.toString(args)+"]");
    }
    //想在目标方法正常结束之后执行
    @AfterReturning(value = "hahaMyPoint()",returning = "result")
    public static void logReturn(JoinPoint joinPoint,Object result){

        //获取目标方法的执行返回值
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();

        System.out.println("【LogUtils-返回通知】["+name+"]方法结束执行，返回值为"+result);
    }
//想在方法执行异常之后执行
    @AfterThrowing(value = "hahaMyPoint()",throwing = "exception")
    public static void logException(JoinPoint joinPoint,Exception exception){
        System.out.println("【LogUtils-异常通知】["+joinPoint.getSignature().getName()+"]方法出现异常，"+exception);
    }
//想在目标方法执行结束之后执行
    @After("hahaMyPoint()")
    public int logEnd(JoinPoint joinPoint){
        System.out.println("【LogUtils-后置通知】["+joinPoint.getSignature().getName()+"]方法最终结束了。。。。");
        return 0;
    }

}


/**


 环绕哦前置通知.....[add]目标方法开始执行....
 [add]方法开始执行，用的参数列表为[[1, 2]]
 方法内部执行。。。。。
 [add]方法结束执行，返回值为3
 [add]方法最终结束了。。。。
 环绕哦返回通知.....[add]方法返回值为:3
 环绕哦后置通知.....[add]方法结束
 */