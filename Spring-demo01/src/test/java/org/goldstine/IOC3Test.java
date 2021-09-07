package org.goldstine;

import org.goldstine.bean.AirPlane;
import org.goldstine.bean.Book;
import org.goldstine.bean.Car;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOC3Test {

    ApplicationContext ioc=new ClassPathXmlApplicationContext("ioc3.xml");

    @Test
    public void test01(){
        //对于bean之间的依赖进行测试

        //多实例bean在容器启动时不会自动创建
        //多实例bean对象再容器获取对应的bean对象时创建
        Car car02 = (Car)ioc.getBean("car02");
        Car car03 = (Car)ioc.getBean("car02");
        System.out.println(car02==car03);
        System.out.println(car02);
    }

    @Test
    public void test02(){
        AirPlane airPlane01 = (AirPlane)ioc.getBean("airPlane01");
        System.out.println(airPlane01);
    }

//    测试通过实例工厂的方式获得bean对象
    @Test
    public void test03(){
        AirPlane airPlane02 = (AirPlane)ioc.getBean("airPlane02");
        //通过实例工厂获得bean实例
        System.out.println(airPlane02);
    }

    /**
     * 除了多实例bean对象在获取对应的bean对象的时候创建，对应的单实例
     * 和通过静态工厂或者通过实例工厂获取的bean实例，都是在容器启动之前就已经创建完成
     */


    /**
     * FactoryBean是Spring规定的一个接口，只要是这个接口的实现类，spring都认为是一个工厂
     *所以除了自己定义对应的静态工厂或者实例工厂
     * 还可以实现接口FactoryBean
     */
    @Test
    public void test04(){
        Book myFactoryBean01 =(Book) ioc.getBean("myFactoryBean01");
        Book myFactoryBean02 =(Book) ioc.getBean("myFactoryBean01");
        System.out.println(myFactoryBean01==myFactoryBean02);  //单实例的时候，对应的bean对象只会创建一个 getObject()方法只会调用一次
        System.out.println(myFactoryBean01);

    }

}
