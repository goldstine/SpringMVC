package org.example.test;

import org.goldstine.bean.Employee;
import org.goldstine.dao.EmployeeDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TxTest {

    /**
     * 创建jdncTemplate对象的一种方式是：通过new通过数据源创建对象
     * 方式二：通过配置文件创建一个对象
     */

    //创建ioc容器
    ApplicationContext ioc=new ClassPathXmlApplicationContext("applicationContext.xml");


    //通过ioc容器中获取对应的bean对象
    JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);

    //从ioc容器中获取一个具名jdbcTemplate对象  NamedParameterJdbcTemplate

    NamedParameterJdbcTemplate namedParameterJdbcTemplate = ioc.getBean(NamedParameterJdbcTemplate.class);

    @Test
    public void test01(){

        //首先创建jdbcTemplate对象
        //创建jdbcTemplate对象需要通过数据源创建
//        DataSource bean = ioc.getBean(DataSource.class);
//
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(bean);
//
//        System.out.println(jdbcTemplate);

        String update_str="UPDATE employee SET salary=? WHERE emp_id=?";

        int update = jdbcTemplate.update(update_str,1300,1);
        System.out.println(update);


    }

//    测试批量插入
    @Test
    public void test02(){
        String sql="INSERT INTO employee(emp_name,salary) VALUES(?,?)";
        //通过jdbcTemplate执行批量插入
        //updare具有增删改三合一的方法，

        //List<Object[]>  List集合的长度就是sql语句要执行的次数
        //Object[]:每次执行要用的参数
        List<Object[]> batchArgs=new ArrayList<Object[]>();
        //每一次执行都需要两个参数
        batchArgs.add(new Object[]{"张三",998.98});
        batchArgs.add(new Object[]{"李四",1212.23});
        batchArgs.add(new Object[]{"王五",3232.12});

        int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }

//    查询数据库
    /**
     * 直接进行查询，数据库表对应的字段名不能与对应的javabean属性对应起来，所以封装失败
     * 如果使用的mybatis框架，存在默认的对应规则，数据库下划线对应javabean驼峰命名法
     *此时可以通过sql起别名的方式将数据库表字段与javabean对应封装
     *查询一条记录与查询多条记录在方法级别进行区分
     *
     */
    @Test
    public void test03(){
        String sql="SELECT emp_id empId,emp_name empName,salary FROM employee WHERE emp_id=?";

        //RowMapper相当于每一行记录和javabean的属性如何进行映射，属于sprin的一个接口

        Employee employee = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Employee.class), 5);
        System.out.println(employee);


    }

    //查询所有的集合信息
    @Test
    public void test04(){
        String sql="SELECT emp_id empId,emp_name empName,salary FROM employee WHERE salary>?";
        //查询工资大于4000

        List<Employee> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class), 4000);

        for (Employee employee : query) {
            System.out.println(employee);

        }

    }


    //查询最大的salary
    @Test
    public void test05(){
        String sql="select max(salary) from employee";
        //无论是返回单个数据还是单个对象，都是通过调用queryForObject
        Double aDouble = jdbcTemplate.queryForObject(sql, Double.class);
        System.out.println(aDouble);
    }

    /**实验七
     * 使用带有具名参数的sql语句插入一条员工记录，并以map形式传入参数
     * 具名参数：（具有名字的参数，参数不是占位符，而是一个变量名）
     *      语法格式    :参数名
     *  占位符参数：？的顺序前往不能乱，传参的时候一定注意
     *
     *  Spring有一个支持具名参数的jdbcTemplate
     *
     */
    @Test
    public void test06(){
        String sql="INSERT INTO employee(emp_name,salary) VALUES(:empName,:salary)";

        //将对应的参数名与参数值进行封装成一个map
        Map<String,Object> paramMap=new HashMap<>();

        paramMap.put("empName","王五");
        paramMap.put("salary",1223.12);

        //通过namedParameterJdbcTemplate
        int update = namedParameterJdbcTemplate.update(sql, paramMap);
        System.out.println(update);

    }

    /**
     * 以SqlParameterSource形式传入参数
     */
    @Test
    public void test08(){
        String sql="INSERT INTO employee(emp_name,salary) VALUES(:empName,:salary)";
        Employee employee = new Employee();
        employee.setEmpName("哈哈");
        employee.setSalary(8889.12);

        int update = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(employee));
        System.out.println(update);
    }

    private EmployeeDao employeeDao;
    //测试通过employeeDao添加数据
    @Test
    public void test09(){
        employeeDao=(EmployeeDao)ioc.getBean(EmployeeDao.class);
        Employee employee = new Employee();
        employee.setEmpName("哈哈");
        employee.setSalary(8889.12);
        employeeDao.saveEmployee(employee);
    }

}
