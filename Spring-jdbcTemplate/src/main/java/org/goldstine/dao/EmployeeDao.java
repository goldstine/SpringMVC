package org.goldstine.dao;

import org.goldstine.bean.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDao {
    //通过jdbcTemplate操作数据库

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveEmployee(Employee employee){
        String sql="INSERT INTO employee(emp_name,salary) VALUES(?,?)";
        //通过jdbcTemplate插入对应的数据
        jdbcTemplate.update(sql,employee.getEmpName(),employee.getSalary());
    }
}
