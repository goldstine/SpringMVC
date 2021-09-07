package org.goldstine.dao;

import org.goldstine.bean.Employee;

/**
 * mybatis全局配置文件
 * mybatis映射文件
 * mybatis动态sql
 * mybatis缓存机制
 * mybatis与Spring整合
 * Mybatis逆向工程
 */


/**
 * Mybatis是支持定制化sql,存储过程以及高级映射的优秀的持久层框架，MyBatis可以对配置和原生的map使用简单的xml或注解，将接口和
 * java的pojos映射成数据库中的记录
 *
 * (1)导入对应的依赖：mysql-connector；mybatis log4j
 * (2)写配置：
 *      1、第一个配置文件（成为mybatis的全局配置文件，指导mybatis如何正确运行，比如连接向哪一个数据库）
 *      2、第二个配置文件：（编写每一个方法都如何向数据库发送sql）相当于是一个实现类
 *
 *      获取到的mapperDao是接口的代理对象，mybatis自动创建的
 *      SqlSessionFactory和SqlSession
 *          SqlSessionFactory创建SqlSession对象，Factory只需要new一次就行
 *          SqlSession相当于connection和数据库进行交互的，和数据库的一次会话，
 *
 */


public interface EmployeeDao {


    public Employee getEmployeeById(Integer id);

    public int updateEmployee(Employee employee);
    public boolean deleteEmployee(Integer id);
    public int insertEmployee(Employee employee);
    
}
