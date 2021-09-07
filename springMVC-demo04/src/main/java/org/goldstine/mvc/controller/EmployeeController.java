package org.goldstine.mvc.controller;

import org.goldstine.mvc.bean.Employee;
import org.goldstine.mvc.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

@Controller
public class EmployeeController {
    //直接在控制层访问dao,这里由于对应的dao类标记为@Repository，注册到对应的ioc容器组件管理
    @Autowired
    private EmployeeDao employeeDao;

    //查询所有员工信息
    @RequestMapping(value = "/employee",method = {RequestMethod.GET})
    public String qeury(Model model){

        System.out.println("查询所有员工信息");
        Collection<Employee> emp = employeeDao.getAll();
        System.out.println(emp);

        //将数据放到对应的域对象共享
        model.addAttribute("employeeList",emp);
        return "employee_list";

    }

    //根据id删除对应的员工信息
    @RequestMapping(value = "/employee/{id}",method = RequestMethod.DELETE)
    public String deleteById(@PathVariable("id") Integer id){
        System.out.println("删除对应的id的员工信息："+id);
        employeeDao.delete(id);
        return "redirect:/employee";//如果操作成功，域原来的请求无关，所以最好使用重定向
    }

    //添加一个用户
    @RequestMapping(value = "/employee",method = RequestMethod.POST)
    public String addEmployee(Employee employee){
        employeeDao.save(employee);
        return "redirect:/employee";
    }

    //根据id查询用户
    @RequestMapping(value = "/employee/{id}",method = RequestMethod.GET)
    public String getEmployeeById(@PathVariable("id") Integer id,Model model){
        //通过Model共享请求查询的数据
        Employee employee = employeeDao.get(id);
        model.addAttribute("employee",employee);//将查询的数据放到共享数据域对象，然后到页面进行回显
        return "employee_update";
    }

    //将修改之后的回显表单进行修改
    @RequestMapping(value = "/employee",method = RequestMethod.PUT)
    public String update(Employee employee){
        employeeDao.save(employee);

        return "redirect:/employee";
    }

}
