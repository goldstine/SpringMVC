package org.goldstine;

import org.goldstine.bean.Book;
import org.goldstine.bean.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOC4Test {

    ConfigurableApplicationContext ioc=new ClassPathXmlApplicationContext("ioc04.xml");

    //对bean生命周期的初始化方法和销毁方法进行创建

    /**
     * 单实例的bean的生命周期：  构造器-----》初始化方法---->销毁方法
     * 容器启动，调用对应的构造器方法
     *多实例的bean的生命周期：（在获取bean的时候创建bean对象）构造器---->初始化方法--》容器关闭不会调用对应的销毁方法
     */
    @Test
    public void test01(){
        Book book01 = (Book) ioc.getBean("book01");
//        Object book01 = ioc.getBean("book01");
        System.out.println("容器关闭，将对应的bean对象进行销毁"+book01);
         ioc.close();
    }


    //测试自动装配，自动赋值
    @Test
    public void test02(){
        Person bean = ioc.getBean(Person.class);
        System.out.println(bean);
    }

//    通过构造器进行赋值
    @Test
    public void test03(){
        System.out.println("++++++++++++++++++++++");
        Person bean = ioc.getBean(Person.class);
        System.out.println(bean);
    }


    //测试通过spel表达式进行赋值
    @Test
    public void test04(){

        System.out.println("测试spel表达式进行赋值。。。。。");

        Person person01 = (Person)ioc.getBean("person01");

        System.out.println(person01);


    }
}
