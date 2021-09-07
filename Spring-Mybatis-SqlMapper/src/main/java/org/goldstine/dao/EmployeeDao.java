package org.goldstine.dao;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.goldstine.bean.Employee;

import java.util.List;
import java.util.Map;

/**
 * 动态sql:
 * (1)if    where   foreach   sql片段  set
 * （2）mybatis缓存
 *          mybati对应的一级缓存对应的是一个sqlSession  同一个sqlSession使用自己的一级缓存
 *          默认对应的一级缓存是开启的
 *          同一个sqlSession进行相同的查询，如果是第一次查询会将查询结果放入一级缓存中
 *
 *      对应的二级缓存，是通过一个dao接口的实现类mapper.xml
 *      对于所有相同的或者不同的sqlSession，如果是同一个namespace进行相同的查询，获得的是二级缓存的数据
 *
 *      默认二级缓存是关闭的，如果需要开启对应的二级缓存，可以在主配置文件配置对应的setting
 *
 *
 */


public interface EmployeeDao {
    public Employee getEmployeeById(Integer id);

    //通过@Param()将告诉mybatis将参数封装成指定的key
    public Employee getEmployeeByIdAndempName(@Param("id") Integer id, @Param("empName") String empName);

    //对于传递pojo类型的参数
    public Employee getEmployeeByIdAndEmpNamewithMap(Map<String,Object> map);

    public boolean deleteEmployee(Integer id);
    public int updateEmployee(Employee employee);
    public int insertEmployee(Employee employee);
    public int insertEmployee1(Employee employee);


    public List<Employee> getAllEmps();
    //查询结果返回对应的map
    public Map<String,Object> getEmpByIdRetureMap(Integer id);
    //查询多个结果返回一个map集合
    @MapKey("id")//将查询的id值作为map的键进行封装
    public Map<Integer,Employee> getAllEmpsRetureMap();

//    动态sql
    /**
     * 动态sql是mybatis非常强大的一个功能，例如一些常见的查询场景
     * 查询田间不确定，批量插入
     * mybatis提供的动态sql节点非常多：
     * （1）if
     * if是一个判断节点，如果满足某个条件，节点中的sql就会生效，例如对于分页查询，要传递两个参数（页码，和要查询的记录数），
     * 如果这两个参数都尉null,表示查询所有
     *
     */

    List<Employee> getEmpByPage(@Param("start") Integer start,@Param("count") Integer count);

    /**
     * 测试通过where来将查询条件进行拼接
     */
    List<Employee> getEmpByUsernameAndId(@Param("id") Integer id,@Param("empname") String empName);


    /**
     * foreach用来处理数组、几何参数
     *
     */
    //对于批量查询
    List<Employee> getUserByIds(@Param("ids") Integer[] ids);//对于通过id数组进行查询

//    批量插入操作
    Integer batchInsertEmp(@Param("emps") List<Employee> emp);


    //sql片段
    /**
     * 在sql查询中，不建议写*，因为select *会降低效率，但是每次查询都要将所有的字段名列出来，太麻烦，所以可以通过
     * sql片段的方式
     *
     */


    /**
     * set关键字一般用在更新中，因为大部分情况下，更新的字段可能不确定，如果对象中存在该字段的值，就更新该字段，不存在，就不更新
     *
     */
    Integer updateEmp(@Param("emp") Employee employee);

}
