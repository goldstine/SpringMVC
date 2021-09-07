package org.goldstine;

import org.goldstine.bean.Book;
import org.goldstine.bean.Car;
import org.goldstine.bean.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class IOC2Test {

    //首先获取ioc2对应的容器
    ApplicationContext ioc=new ClassPathXmlApplicationContext("ioc2.xml");

    @Test
    public void test(){
        //对各种属性进行赋值
        //测试使用null值，默认引用数据类型null   基本类型是默认值
        Person person01 = (Person) ioc.getBean("person01");
        System.out.println(person01);
        System.out.println(person01.getLastName()==null);
        System.out.println(person01.getCar());

        //直接通过容器获得的car对象和通过Person01引用的对象car是同一个对象
        Car car = (Car)ioc.getBean("car01");
        System.out.println(car==person01.getCar());

        //如果将容器中的car对象变化，得到的通过person01对象获得的car也会发生变化
        System.out.println("通过person01得到的car:"+person01.getCar());
        //将容器中的car进行修改
        car.setCarName("haha自行车");

        //查看是否person01获得的对应的car是否发生变化
        System.out.println("通过person01获得的car是否发生变化："+person01.getCar());//所以应用的是同一个对象

    }
    @Test
    public void test01(){
        Person person02 =(Person) ioc.getBean("person02");
        List<Book> books = person02.getBooks();
        System.out.println(books);

//        Car bean = (Car)ioc.getBean("book000x");
//        System.out.println(bean);//NoSuchBeanDefinitionException: No bean named 'book000x' available
        //内部的bean是不能够通过ioc容器通过id进行获取的

        //测试person02对象中的maps集合对象
        Map<String, Object> maps = person02.getMaps();
        System.out.println(maps);
        //通过lambda遍历对应的map集合
        maps.forEach((k,v)->{System.out.println(k+"====="+v);});


        //获得对应的properties对象
        Properties properties = person02.getProperties();
        System.out.println(properties);//该对象作为一个map集合对象
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        System.out.println("properties对象："+username+"password:"+password);

    }

    @Test
    public void test02(){
        //获取对应的person03
        Person person03 = (Person)ioc.getBean("person03");
        System.out.println(person03.getMaps());


        //容器可以通过id获取到对应的util:map集合
        Object myMap = ioc.getBean("myMap");
        System.out.println(myMap);
        System.out.println(myMap.getClass());
    }

    /**
     * 级联属性可以修改属性的属性，注意：原来的bean的值可能会被修改
     */
    @Test
    public void test03(){
        //通过级联属性赋值
        Person person03 = (Person)ioc.getBean("person04");

        //首先输出容器中的car对象
        Car c = (Car)ioc.getBean("car001");
        System.out.println(c);

        Car car = person03.getCar();
        System.out.println(car.getPrice());
    }

    //测试通过parent的方式实现bean的配置信息之间的继承
    @Test
    public void test04(){
        Person person06 = (Person)ioc.getBean("person06");
        Person person05 = (Person) ioc.getBean("person05");

        //对应的父bean中的配置信息
        System.out.println(person05);
        //继承货的配置信息
        System.out.println(person06);
    }

    //配置为abstract=true的bean不能够通过容器获取
    @Test
    public void test05(){
//        Person person07 = (Person) ioc.getBean("person07");
//        System.out.println(person07);
    }

}
