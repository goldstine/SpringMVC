package org.example.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.goldstine.bean.Key;
import org.goldstine.dao.KeyDao;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class KeyTest {

    /**
     * 测试联表查询
     */

    SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() throws IOException {
        //创建一个sqlSessionFactory
        String resource="mybatis-config.xml";
        //通过Resources将主配置文件转为一个输入字节流
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);

        //创建一个sqlSessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

    }

    @Test
    public void test01(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        try{
            KeyDao mapper = sqlSession.getMapper(KeyDao.class);
            Key keyById = mapper.getKeyById(1);
            System.out.println(keyById);
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test02(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        try{
            KeyDao mapper = sqlSession.getMapper(KeyDao.class);
            Key keyByIdSimple = mapper.getKeyByIdSimple(1);
            System.out.println(keyByIdSimple);
        }finally {
            sqlSession.close();
        }
    }
}
