package org.example.test;

import org.junit.Test;

public class TxTest {

    /**
     * springioc容器提供对应的jdbcTemplate实体bean,所以直接通过依赖注入即可使用
     * 或者通过ioc.getBean()的方式获得对应的jdbcTemplate对象，对数据库jdbc进行操作
     */
    @Test
    public void test01(){

    }
}
