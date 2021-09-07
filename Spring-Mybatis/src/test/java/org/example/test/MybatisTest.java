package org.example.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.goldstine.bean.Employee;
import org.goldstine.dao.EmployeeDao;
import org.goldstine.dao.EmployeeDaoAnnotation;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;


public class MybatisTest {
    SqlSessionFactory sqlSessionFactory;
    @Before
    public void init() throws IOException {
        ///首先通过主配置文件获取对应的sqlSessionFactory
        String resource="mybatis-config.xml";
        //通过ibatis的resources类对象将主配置文件转为输入字节流
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        //通过构建sqlSessionFactory
        sqlSessionFactory= new SqlSessionFactoryBuilder().build(resourceAsStream);
    }


    @Test
    public void test() throws IOException {
        //根据全局配置文件创建出一个SqlSessionFactory
        String resource="mybatis-config.xml";
        //通过IBatis包下的Resource类对象将配置文件读取为输入字节流
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);

        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //sqlSessionFactory,是sqlSession工厂，负责创建SqlSession对象
        //sqlSession:sql会话（代表和数据库的一次会话）

        //获取和数据库的一次会话：getConnection()
        SqlSession sqlSession = sessionFactory.openSession();

        //使用sqlSession操作数据库，获取到dao接口的实现
        Employee employee;
        try {
            EmployeeDao mapper = sqlSession.getMapper(EmployeeDao.class);

            employee = mapper.getEmployeeById(1);
        } finally {
            sqlSession.close();
        }
        System.out.println(employee);

    }


//    crud测试
    @Test
    public void test02() throws IOException {
//        init();可以直接在对应的Test上添加before
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//设置自动提交

        //通过sqlSession获得dao接口的实现类对象
        EmployeeDao mapper = sqlSession.getMapper(EmployeeDao.class);
        System.out.println(mapper.getClass());//class com.sun.proxy.$Proxy8
        //mapper作为代理对象，所以我们没有为接口写对应的是实现类，mybatis自动为接口创建实现类的代理对象

        Employee employeeById = mapper.getEmployeeById(1);
        System.out.println(employeeById);

        int res = mapper.insertEmployee(new Employee(2,"wangwu", 0, "zhangsan@qq.com","通过注解"));
        System.out.println(res);

        //默认情况下，对应的sql语句的执行结果没有提交
//        sqlSession.commit();
    }

    //测试基于注解版的CRUD
    @Test
    public void test03(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        EmployeeDaoAnnotation mapper = sqlSession.getMapper(EmployeeDaoAnnotation.class);

        Employee employeeById = mapper.getEmployeeById(1);
        System.out.println(employeeById);
    }


    //测试sql映射文件的属性配置
    @Test
    public void test04(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        try {
            EmployeeDao mapper = sqlSession.getMapper(EmployeeDao.class);
//            Employee employee = new Employee(null, "hahah", 0, "haha@qq.com", "121");
//            int insertEmployee = mapper.insertEmployee(employee);

            Employee employee = new Employee(30, "hahah", 0, "haha@qq.com", "121");
            int insertEmployee = mapper.insertEmployee(employee);

            //获取刚才的添加的自增id
            System.out.println(employee.getId());
            System.out.println(insertEmployee);
        }finally {
            sqlSession.close();
        }
    }


}
