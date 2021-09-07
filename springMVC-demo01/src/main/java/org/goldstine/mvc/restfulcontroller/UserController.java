package org.goldstine.mvc.restfulcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {


    //页面test_rest.html的请求路径
    @RequestMapping("/test_rest")
    public String testRest(){
        return "test_rest";
    }

    /**
     * 使用Restful风格模拟用户的增删改查
     * /user   Get  查询所有的用户
     * /user/1  GET  根据用户id查询用户信息
     * /user POST   添加用户信息
     * /user/1  Delete   根据id删除用户
     * /user   PUT  修改用户
     */

    //获取所有的用户信息
    @RequestMapping(value = "/user",method = {RequestMethod.GET})
    public String getUsers(){
        System.out.println("查询所有用户信息");
        return "target";
    }

    //根据id产找用户信息
    @RequestMapping(value = "/user/{id}",method = {RequestMethod.GET})
    public String getUserById(@PathVariable("id") Integer id){

        System.out.println("根据对应的id查找某一个用户信息:"+id);
        return "target";
    }

    //添加一个用户信息
    @RequestMapping(value = "/user",method = {RequestMethod.POST})
    public String insertUser(String username,String password){
        System.out.println("添加一个用户信息:"+username+"password:"+password);
        return "target";
    }

    /**
     * 删除用户的信息，同样也需要表单提交post请求
     * @return
     */

    //根据id删除一个用户信息
    @RequestMapping(value = {"/user/{id}"},method = {RequestMethod.DELETE})
    public String deleteUser(String username,String password){

        System.out.println("根据id删除一个用户:"+username+"password:"+password);
        return "target";
    }

    //修改用户的信息
    @RequestMapping(value = {"/user"},method = {RequestMethod.PUT})
    public String updateUser(String username,String password){
        System.out.println("修改一个用户信息:"+username+"password:"+password);
        return "target";
    }
    /**
     * 通过过滤器进行转换请求方式，客户端还是通过post进行提交，只不过，通过浏览器客户端页面表单提交一个hidden隐藏字字段
     * _method  对应的职位PUT请求   通过Filter实现类 HiddenHttpMethodFilter
     * 在对应的web.xml配置文件中配置
     */
}
