package org.example.test;

import org.goldstine.impl.MyMathCalculator;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTest {

    ApplicationContext ioc=new ClassPathXmlApplicationContext("applicationContext.xml");

    @Test
    public void test02(){

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
