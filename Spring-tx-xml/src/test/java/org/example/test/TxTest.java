package org.example.test;

import org.goldstine.service.BookService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TxTest {

    ApplicationContext ioc=new ClassPathXmlApplicationContext("applicationContext.xml");

    @Test
    public void test(){
        BookService bean = ioc.getBean(BookService.class);
        bean.checkout("Tom","ISBN-001");
        System.out.println(bean.getClass());
    }
}
