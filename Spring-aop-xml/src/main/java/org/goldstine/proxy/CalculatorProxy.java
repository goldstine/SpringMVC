package org.goldstine.proxy;//package org.goldstine.proxy;
//
//import org.goldstine.inter.Calculator;
//import org.goldstine.utils.LogUtils;
//
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.lang.reflect.Proxy;
//import java.util.Arrays;
//
///**
// * 创建代理对象的类
// */
//public class CalculatorProxy {
//    /**
//     * 传入被代理对象作为参数
//     * jdk底层提供对应的Proxy代理类
//     * @return
//     */
//    public static Calculator getProxy(final Calculator calculator){
//
////        ClassLoader loader,  获取需要被代理对象的类加载器
////        Class<?>[] interfaces,//获取被代理对象类实现的所有接口
////        InvocationHandler 获取InvocationHandler处理器对象
//
//        ClassLoader classLoader = calculator.getClass().getClassLoader();
//        Class<?>[] interfaces = calculator.getClass().getInterfaces();
//        InvocationHandler h=new InvocationHandler() {
//            /**
//             *
//             * @param proxy  代理对象，给jdk使用，任何时候都不要动这个对象
//             * @param method  当前将要执行的目标对象的方法
//             * @param args    这个方法调用时外界传入的参数值
//             * @return         //存在对应的返回值，是目标方法执行之后的返回值
//             * @throws Throwable
//             */
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                //利用反射执行目标方法
//                //存在对应的返回值，是目标方法执行之后的返回值
////                System.out.println("这是动态代理将要帮你执行方法。。。。");
//
//                Object result = null;
//                try {
////                    System.out.println("["+method.getName()+"]方法开始执行，用的参数列表为["+ Arrays.asList(args)+"]");
//                    //将具体的执行任务进行解耦抽取，全部都放在LogUtils工具类中
//                    LogUtils.logStart(method,args);
//                    result = method.invoke(calculator, args);
////                    System.out.println("["+method.getName()+"]方法执行完成，计算结果是："+result);
//                    LogUtils.logReturn(method,result);
//                } catch (IllegalAccessException e) {
//                    LogUtils.logException(method,e);
////                    System.out.println("["+method.getName()+"]方法执行出现异常了，异常信息是："+e.getCause()+";这个异常已经通知测试小组进行排除....");
//                }finally {
////                    System.out.println("["+method.getName()+"]方法最终之行结束了....");
//                    LogUtils.loeEnd(method);
//                }
//
//                //返回值必须返回出去外界才能真正执行后的返回值
//                return result;
//            }
//        };
//
//        Object proxyInstance = Proxy.newProxyInstance(classLoader, interfaces, h);
//        return (Calculator)proxyInstance;
//    }
//}
