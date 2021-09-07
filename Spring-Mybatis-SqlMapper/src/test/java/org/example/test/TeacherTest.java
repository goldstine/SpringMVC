package org.example.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.goldstine.bean.Teacher;
import org.goldstine.dao.TeacherDao;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class TeacherTest {

    SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() throws IOException {
        String source="mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(source);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

    }

    @Test
    public void test01(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        try{
            TeacherDao mapper = sqlSession.getMapper(TeacherDao.class);
            Teacher teacherById = mapper.getTeacherById(1);
            System.out.println(teacherById);
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test02(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        try{
            TeacherDao mapper = sqlSession.getMapper(TeacherDao.class);
            Teacher teacher = new Teacher();

            teacher.setId(1);
            teacher.setName("%a%");
//            teacher.setBirth(new Date());

            List<Teacher> list = mapper.getTeacherByCondition(teacher);
            System.out.println(list);
        }finally {
            sqlSession.close();
        }
    }
}
