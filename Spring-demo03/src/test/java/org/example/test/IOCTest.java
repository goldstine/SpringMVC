package org.example.test;

import org.goldstine.service.BookService;
import org.goldstine.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class IOCTest {


    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Test
    public void test01(){
        bookService.save();
        userService.save();

        System.out.println(bookService.getClass());
        //父类类型
        System.out.println(bookService.getClass().getSuperclass());
        //获取带泛型的父类
        System.out.println(bookService.getClass().getGenericSuperclass());

        //Spring中可以使用带泛型的父类类型来确定这个字类的类型

        System.out.println(userService.getClass());
        System.out.println(userService.getClass().getSuperclass());
        System.out.println(userService.getClass().getGenericSuperclass());
    }

    /**
     * IOC总结：
     * ioc是一个容器，帮我们管理所有组件
     * （1）依赖注入，@Autowire:自动赋值
     * （2）某个组件要使用Spring提供的更多（IOC,AOP）必须加入到容器中
     * （3）调试spring源码，容器到底是什么？其实就是一个map
     * 这个map中保存所有创建好的bean,并且提供外界获取功能。。。。
     * 单实例的bean都保存在哪一个map中了
     * 源码调试的思路：从helloworld开始
     */
}
