package org.example.test;

import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.goldstine.bean.Employee;
import org.goldstine.dao.EmployeeDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisTest {

    //创建一个sqlSessionFactory
   SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() throws IOException {
        String source="mybatis-config.xml";
        //通过Resource将文件转换成输入字节流
        InputStream resourceAsStream = Resources.getResourceAsStream(source);
        //通过对应得输入字节流获得对应的sqlSessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
    }

    @Test
    public void test01(){
        //通过获取的sqlSessionFactory获得一个SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //通过sqlSession获得mapper代理对象
        EmployeeDao mapper = sqlSession.getMapper(EmployeeDao.class);
        Employee employeeById = mapper.getEmployeeById(6);
        System.out.println(employeeById);
    }

    //策测试通过sql映射文件获取自增id
    @Test
    public void test02(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        try{
            EmployeeDao mapper = sqlSession.getMapper(EmployeeDao.class);
            Employee employee = new Employee(null,"haha", 0, "zhas@qq.com", "asas");

            int i = mapper.insertEmployee1(employee);
            //获得对应的自增id
            System.out.println("通过属性获得自增id:"+employee.getId());
            System.out.println(i);
        }finally {
            sqlSession.close();
        }
    }

//测试通过id和名字查询employee
    @Test
    public void test03(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        try{
            EmployeeDao mapper = sqlSession.getMapper(EmployeeDao.class);
            Employee admin = mapper.getEmployeeByIdAndempName(1, "admin");
            System.out.println(admin);

        }finally {
            sqlSession.close();
        }
    }

//测试通过map传递参数
    @Test
    public void test04(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        try{
            EmployeeDao mapper = sqlSession.getMapper(EmployeeDao.class);
            Employee admin = mapper.getEmployeeByIdAndempName(1, "admin");
            System.out.println(admin);

            System.out.println("===============");

            Map<String,Object> map=new HashMap<>();
            map.put("id",1);
            map.put("empName","admin");
            Employee employee = mapper.getEmployeeByIdAndEmpNamewithMap(map);

            System.out.println(employee);


        }finally {
            sqlSession.close();
        }
    }


//    对if判断节点的分页查询进行测试
    @Test
    public void test05(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        try{
            //通过sqlSession获得mapper
            EmployeeDao mapper = sqlSession.getMapper(EmployeeDao.class);
            List<Employee> empByPage = mapper.getEmpByPage(null, null);
            System.out.println(empByPage);
            //传入参数进行查询
            List<Employee> empByPage1 = mapper.getEmpByPage(0, 4);
            //start参数表示从数据库表中的开始查询字段，count表示从数据库表中查询的数量
            System.out.println(empByPage1);
        }finally {
            sqlSession.close();
        }
    }

    //测试对应的where
    @Test
    public void test06(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        try{
//通过sqlSession获得mapper
            EmployeeDao mapper = sqlSession.getMapper(EmployeeDao.class);
            List<Employee> list = mapper.getEmpByUsernameAndId(6, "张");
            System.out.println(list);
            List<Employee> wang = mapper.getEmpByUsernameAndId(null, "wang");
            System.out.println(wang);
            List<Employee> empByUsernameAndId = mapper.getEmpByUsernameAndId(32, null);
            System.out.println(empByUsernameAndId);
            List<Employee> empByUsernameAndId1 = mapper.getEmpByUsernameAndId(null, null);
            System.out.println(empByUsernameAndId1);
        }finally {
        sqlSession.close();
        }
    }

    //测试通过foreach的方式进行查询
    @Test
    public void test07(){
        //首先获取对应的sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        try{
            //通过sqlSession获得mapper
            EmployeeDao mapper = sqlSession.getMapper(EmployeeDao.class);
            //创建参数列表
            Integer[] ids={1,10,16,46};
            List<Employee> userByIds = mapper.getUserByIds(ids);
            System.out.println(userByIds);
        }finally {
            sqlSession.close();
        }
    }

    //批量插入的测试
    @Test
    public void test08(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        try{
            EmployeeDao mapper = sqlSession.getMapper(EmployeeDao.class);
            List<Employee> emps = new ArrayList<>();

            Employee employee = new Employee();
            employee.setEmpName("goldstine");
            employee.setGender(0);
            employee.setEmail("liulei@qq.com");
            employee.setLoginAcc("liulei");

            Employee employee1 = new Employee();
            employee1.setEmpName("gol");
            employee1.setGender(0);
            employee1.setEmail("liu@qq.com");
            employee1.setLoginAcc("llei");

            emps.add(employee);
            emps.add(employee1);

            Integer integer = mapper.batchInsertEmp(emps);

            System.out.println(integer);

        }finally {
            sqlSession.close();
        }
    }

//    测试动态SQL  set
    @Test
    public void test09(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        try{
            EmployeeDao mapper = sqlSession.getMapper(EmployeeDao.class);
            Integer integer = mapper.updateEmp(new Employee(49, "张飞", 1, "zhangfei@qq.com", "za"));
            System.out.println(integer);
        }finally {
            sqlSession.close();
        }
    }

    //测试查询集合
    @Test
    public void test10(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            EmployeeDao mapper = sqlSession.getMapper(EmployeeDao.class);
            List<Employee> allEmps = mapper.getAllEmps();
            System.out.println(allEmps);

            //对查询的map进行测试
            Map<String, Object> empByIdRetureMap = mapper.getEmpByIdRetureMap(1);
            System.out.println(empByIdRetureMap);

            //查询多个记录返回一个map
            Map<Integer, Employee> allEmpsRetureMap = mapper.getAllEmpsRetureMap();
            System.out.println(allEmpsRetureMap);

        }finally {
            sqlSession.close();
        }
    }

}
