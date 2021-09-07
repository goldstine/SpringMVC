package org.example.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.goldstine.bean.Key;
import org.goldstine.bean.Lock;
import org.goldstine.dao.LockDao;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class LockTest {

    //初始化工厂

    SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() throws IOException {
        String source="mybatis-config.xml";
        //通过resources将配置文件转为输入字节流
        InputStream resourceAsStream = Resources.getResourceAsStream(source);

        //创建sqlSessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);


    }

    @Test
    public void test01(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        try{
            LockDao mapper = sqlSession.getMapper(LockDao.class);
            Lock lockById = mapper.getLockById(3);
            System.out.println(lockById);
            System.out.println("所有钥匙如下所示：");
            List<Key> keys = lockById.getKeys();
            for (Key key : keys) {
                System.out.println(key);
            }
        }finally {
            sqlSession.close();
        }
    }


}
