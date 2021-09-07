package org.goldstine;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SourceTest {



    ApplicationContext ioc=null;
    @Test
    public void test(){
        ioc=new ClassPathXmlApplicationContext("ioc2.xml");
        Object person03 = ioc.getBean("person03");
        System.out.println(person03);
    }
}
