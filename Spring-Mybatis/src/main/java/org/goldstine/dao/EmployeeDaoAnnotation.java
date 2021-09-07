package org.goldstine.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.goldstine.bean.Employee;

public interface EmployeeDaoAnnotation {
    @Select("select * from t_employee where id = #{id}")
    public Employee getEmployeeById(Integer id);
    @Update("update t_employee set empName=#{empName},gender=#{gender},email=#{email} where id=#{id}")
    public int updateEmployee(Employee employee);
    @Delete("delete from t_employee where id=#{id}")
    public boolean deleteEmployee(Integer id);
    @Insert("insert into t_employee(empName,gender,email,login_acc) values(#{empName},#{gender},#{email},#{loginAcc})")
    public int insertEmployee(Employee employee);

}
