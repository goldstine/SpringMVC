package org.example.test;

import org.goldstine.service.BookService;
import org.goldstine.service.MulService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileNotFoundException;

public class TxTest {

    //获得ioc容器
    ApplicationContext ioc=new ClassPathXmlApplicationContext("applicationContext.xml");


    /**
     * 声明式事务 ：以前通过复杂的编程来编写一个事务，替换为只需要告诉Spring哪个方法是事务方法即可
     * Spring自动进行事务控制
     * 编程式事务
     *
     * 事务管理代码的固定模式作为一种横切关注点，可以通过aop方法模块化，进而借助Spring AOP框架实现声明式事务
     * 自己要写这个切面还是很麻烦的，这个切面已经有了
     * 事务管理器:PlatformTransactionManager
     *  这个事务管理器就可以在目标方法运行前后进行事务控制（事务切面）
     *  目前使用DataSourceTransactionManager
     *  使用步骤：
     *  （1）首先配置组件ioc
     *
     */


    @Test
    public void test01() throws FileNotFoundException {
        //测试TOm的结账业务
        BookService bookService = ioc.getBean(BookService.class);
//        bookService.checkout("Tom","ISBN-001");
//        System.out.println("结账完成....");


        //测试对应的getPrice(),事务的隔离级别为读未提交

        int price = bookService.getPrice("ISBN-001");
        System.out.println("读取到的数据："+price);

    }


    @Test
    public void test02(){
        BookService bean = ioc.getBean(BookService.class);
        System.out.println(bean.getClass());//class org.goldstine.service.BookService$$EnhancerBySpringCGLIB$$708fc08b
        //对于有事务的业务逻辑，容器中报错的是这个业务逻辑的代理对象
    }

    //对混合事务测试
    @Test
    public void test03(){
        MulService bean = ioc.getBean(MulService.class);
        bean.mulTx();
    }

    //测试通过一个类中的事务调用
    @Test
    public void test04(){
        BookService bean = ioc.getBean(BookService.class);
        bean.mulTx();
    }

}
