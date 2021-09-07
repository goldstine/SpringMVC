package org.example.test;

import org.goldstine.impl.MyMathCalculator;
import org.goldstine.inter.Calculator;
//import org.goldstine.proxy.CalculatorProxy;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class AopTest {

    /**
     * 通过计算器运行计算方法的时候进行日志记录
     *      (1)直接添加在代码内部，不推荐，修改维护麻烦
     *          日志记录：系统的辅助功能
     *          业务逻辑：（核心功能）
     *
     *         最简单的方式，就是将重复执行的代码，抽象出来成为一个Utils类
     *需要实现，日志功能，在程序运行的时候，自动加上日志功能
     *
     * 可以使用动态代理来将日志代码动态地在目标方法执行的前后进行执行
     */

//    @Test
//    public void test01(){
//        MyMathCalculator calculator = new MyMathCalculator();
//        calculator.add(2,3);
//        calculator.div(6,2);
//
//        System.out.println("[=================]");
//
//        //如果是拿到了这个对象的代理对象，代理对象执行加减乘除
//        Calculator proxy= CalculatorProxy.getProxy(calculator);
//        proxy.add(1,2);//通过代理对象类获取对象的代理对象，通过代理对象执行对应的具体方法
////        proxy.div(10,0);
//        /**
//         * 通过代理对象，如果需要输出执行方法的日志信息，直接通过代理对象执行对应的方法
//         */
//
//        //代理对象的类型
//        System.out.println(proxy.getClass());//class com.sun.proxy.$Proxy2    通过getClass()的方式获得的是对应的对象的实际的类
//
//        //代理对象和被代理对象唯一能产生的关联就是实现了同一接口
//        System.out.println(Arrays.toString(proxy.getClass().getInterfaces()));//[interface org.goldstine.inter.Calculator]
//        //因为代理对象也实现了对应的接口，所以可以使用Calculator接口接收
//        /**
//         * jdk默认的动态代理，如果目标对象没有实现任何接口，是无法为它创建代理对象的
//         */
//        //Spring动态代理难，Spring实现了AOP,底层就是动态代理
//        //可以利用Spring一句代码都不写去创建动态代理，实现简单，而且没有强制要求目标对象必须实现接口
//        //将某段代码（日志）动态的切入（不把日志代码写死在业务逻辑方法中）到指定位置的进行运行的这种编程方式（Spring简化了面向切面编程）
//    }


    ApplicationContext ioc=new ClassPathXmlApplicationContext("applicationContext.xml");

    @Test
    public void test02(){
        //直接通过接口实现类，目标类对象进行方法  目标方法调用
//        MyMathCalculator myMathCalculator = new MyMathCalculator();
//        myMathCalculator.add(2,12);
        //从ioc容器中那到对应的目标对象，注意：如果想要用类型，一定用他的接口类型，不要用它的本类
        /**
         * AOP底层就是动态代理，容器中保存的组件是他的代理对象：$Proxy12当然不是本类的类型
         *实际上在容器中创建的是代理对象，并且是单例的
         *
         */

//        Calculator bean =ioc.getBean(Calculator.class);
//        bean.add(2,12);
//        System.out.println(bean);//org.goldstine.impl.MyMathCalculator@313b2ea6
//        System.out.println(bean.getClass());//class com.sun.proxy.$Proxy17
//
//
//        //通过id名的方式获取对应的bean对象
//        Calculator myMathCalculator = (Calculator)ioc.getBean("myMathCalculator");
//        myMathCalculator.add(1,2);
//        System.out.println(myMathCalculator);
//        System.out.println(myMathCalculator.getClass());

        
        //没有接口就是本类类型
        //cglib帮我们创建好的代理对象

        MyMathCalculator bean1 = ioc.getBean(MyMathCalculator.class);
        bean1.add(1,2);
        System.out.println(bean1);//org.goldstine.impl.MyMathCalculator@6fd83fc1
        System.out.println(bean1.getClass());//class org.goldstine.impl.MyMathCalculator$$EnhancerBySpringCGLIB$$18686d55

    }

    @Test
    public void test03(){
        //测试重载的方法匹配
        MyMathCalculator bean = ioc.getBean(MyMathCalculator.class);
        bean.add(1,2.0);
    }

    @Test
    public void test04(){
        //测试目标方法的详细信息
        MyMathCalculator bean = ioc.getBean(MyMathCalculator.class);
        int add = bean.add(1, 2);
        System.out.println("======================"+add);

        bean.div(12,3);
    }
}
