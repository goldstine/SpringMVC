package org.goldstine;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DataSourceTest {
    
    ApplicationContext ioc=new ClassPathXmlApplicationContext("databaseIoc.xml");
    @Test
    public void test01() throws SQLException {
        //从容器中拿到连接池
//        DataSource dataSource =(DataSource) ioc.getBean("dataSource");
        //ComboPooledDataSource是Source是实现子类
        DataSource bean = ioc.getBean(DataSource.class);//通过类型进行获取只能获取一个对应的实体bean
        //通过类型从数据库连接池中进行获取
        System.out.println(bean.getConnection());
    }
}
