package org.goldstine;

import org.goldstine.bean.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class IOCTest {

    private ApplicationContext ioc=new ClassPathXmlApplicationContext("ioc.xml");

    /**
     * 从容器中拿到这个组件
     */
    @Test
    public void test(){
        //ApplicationContext代表IOC容器
        //ClassPathXmlApplicationContext:当前应用的xml配置文件在ClassPath下
        //根据spring配置文件获得ioc容器
        //src源码包开始的路径，称为类路径的开始，所有源码包的东西都会被合并放在类路径中
        /**
         * web项目的类路径：/WEB-INF/classes/
         * java项目：/bin/
         */
        ApplicationContext ioc=new ClassPathXmlApplicationContext("ioc.xml");

        /**
         * 如果是通过类路径获取对应的ioc容器：可以通过ClassPathXmlApplicationContext
         * 如果spring的配置文件不是放在对应的类路径下，也可以通过文件系统在硬盘中获取对应的配置文件ioc容器
         * FileSystemXmlApplicationContext
         *
         *
         * ApplicationContext:IOC容器的接口
         * （1）给容器中注册一个组件，容器就会自动创建一个对象
         * （2）容器创建对象的时间是什么时候？
         *      容器中对象的创建是在容器创建完成的时候就已经创建好了
         *
         *  （3）同一个组件在ioc容器中是单实例的,并且在容器启动之前就已经创建完成的
         *
         * （4） 容器中如果没有这个组件，获取组件会出现对应的异常： No bean named 'person03' available
         *（5）ioc容器在创建这个组件对象的时候，（Property）会利用setter()为javabean的属性进行赋值
         *（6）javabean的属性只是有什么决定的
         * private String lastName;
         *  javabean的属性值是由对应的setter()方法决定的；
         *  getter()、setter()方法是属性名       setter去掉后面的一串首字母小写就是属性名
         *  所有的getter()setter都自动生成
         *
         */
        System.out.println("容器创建了....");
        //在容器中获取对应的id组件
        Person person01 =(Person) ioc.getBean("person01");
        //获取第二次对应的组件对象
        Person person011 = (Person) ioc.getBean("person01");
        System.out.println(person01==person011);//true
        System.out.println(person01);//已经重写了toString()

        //对于配置文件中如果没有对应的组件注册，ioc容器不能够创建对应的组件对象
//        Object person03 = ioc.getBean("person03");
//        System.out.println(person03);// No bean named 'person03' available

    }

    @Test
    public void test02(){
        //上面是根据bean的id从ioc容器中获得bean的实例
        //还可以通过bean的类型获得bean实例
        //如果ioc容器中这个类型的bean有多个，按照类型进行查找会报错NoUniqueBeanDefinitionException
        //此时如果需要通过类型查找，还需要对应的id
//        Person bean = ioc.getBean(Person.class);//这种方式只能获得一个bean实例
        Person bean = ioc.getBean("person01", Person.class);
        System.out.println(bean);
    }

    @Test
    public void test03(){
        //通过构造器为对应的bean实例进行赋值
        Object person03 = ioc.getBean("person03");
        System.out.println(person03);
        Object person04 = ioc.getBean("person04");//通过赋值顺序进行复制的方式初始化bean和为属性赋值
        System.out.println(person04);
        //通过value+index的方式给构造器进行赋值
        Object person05 = ioc.getBean("person05");
        System.out.println(person05);

        //三个参数的测试构造器赋值
        Object person06 = ioc.getBean("person06");
        System.out.println(person06);

        //测试通过p名称空间获取的bean对象
        Object person07 = ioc.getBean("person07");
        System.out.println(person07);
    }

}
