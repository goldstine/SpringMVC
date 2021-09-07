package org.example.test;

import org.goldstine.service.BookService;
import org.goldstine.servlet.BookServlet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class IOCTest {

//    ApplicationContext ioc=new ClassPathXmlApplicationContext("applicationContext.xml");
ApplicationContext ioc=null;

    /**
     * 如果是junit单元测试，会首先创建一个IOCTest对象，创建一个ioc容器，容器启动之前创建所有组件，扫描到，然后又创建IOCTest对象
     */
    /**
     * 可以使用spring的单元测试，首先导入对应的依赖,spring-test
     * @ContextConfiguration(locations="")使用它来指定spring配置文件的位置
     * @RunWith()指定用那种驱动进行单元测试，默认就是junit
     *
     * 好处：不用ioc.getBean（）获取组件，直接@Autowire组件，Spring为我们自动装配
     */

    @Autowired
    BookServlet bookServlet;

    @Test
    public void test(){
        System.out.println(bookServlet);
    }


    @Test
    public void test01(){
        //测试获取通过注解放入ioc容器的组件
        BookServlet bookServlet = (BookServlet)ioc.getBean("haha");
        BookServlet bookServlet1 = (BookServlet)ioc.getBean("haha");
        System.out.println(bookServlet==bookServlet1);//通过注解的方式标记的bean在容器中创建的也是单实例
        System.out.println(bookServlet);
    }

    @Test
    public void test02(){
        //测试context:include-filter:只扫描对应的组件加入容器中
        BookService vk=(BookService) ioc.getBean("bookService");
        System.out.println(vk);
    }

    @Test
    public void test03(){
        //测试通过注解自动装配的
        BookServlet bookServlet = (BookServlet) ioc.getBean("bookServlet");
        bookServlet.doGet();
    }
}
