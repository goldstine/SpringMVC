package org.goldstine.mvc.controller;

import org.goldstine.mvc.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

/**
 * 获取参数的方式，javaweb
 * request.getParameter()
 * request.getParameterValues()返回值是一个字符串类型的数组
 *
 */

/**
 * 请求参数获取：
 * 方式一：
 *  通过servletAPI的HttpServletRequest的方式获得参数
 *  方式二：使用SpringMVC框架，就不要使用原生的Servlet操作，应为所有的参数都通过框架已经获取了
 *  通过控制器方法的形参获取请求参数
 *      在控制器方法的形参位置，设置于请求参数同名的形参，当浏览器发送请求时，匹配到请求映射的时候，在DispatcherServlet中就会将请求参数赋值给相应的形参
 *      ，总之就是通过DispatcherServlet
 *
 */


@Controller
public class ParamControlller {

    @GetMapping("/param")
    public String testParam(){
        return "test_param";
    }

    //通过servlet获取请求参数
    @GetMapping("/testServletApi")
    public String testServletApi(HttpServletRequest request){
        //HttpServletRequest是由tomcat服务器进行管理的，所以对应的参数自动获得实参，所以直接使用
        //注意：如果是通过restful的方式接口，由于传递的参数没有对应的参数名，所以不能够通过name获取对应的参数值，即不能采用request的方式获得请求参数
        //一般情况下很少使用request的方式获得参数
        
        //通过在服务器端创建session

        HttpSession session = request.getSession();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("获取的请求参数:"+username+password);

        return "test_param";
    }


    //通过控制器方法的形参获取参数
    @GetMapping("/testControllerParam")
    public String testControllerParam(String username,String password){
        System.out.println("获取的形参是："+username+password);
        return "test_param";
    }

    //http://localhost:8080/testParam?username=asa&password=1212&hobby=a&hobby=b
    //对于通过控制器形参获取多个同名请求参数的方式
    //String[] hobby
    //对于多个同名参数，可以通过同名字符串数组接收
    //也可直接通过字符串接收，接收的字符串多个参数通过,进行分割

    /**
     *如果形参名与请求参数之间并不对应，也有补救的方式
     * 可以通过注解@ReuestParam进行映射对应的                    开闭原则：对修改关闭，对扩展开放
     * @RequestParam注解的作用是将请求参数与形参之间建立关系
     *
     * @RequestParam注解的定义内部存在属性boolean required() default true;对应的默认值为true，也就是说该属性必须传输，
     * 否者会出现对应的400错误在浏览器页面
     * 所以在使用注解@RequestParam（）时可以设置对应的required=false
     * 对应的属性defaultValue表示默认值，如果请求参数没有该字段的话，就使用默认值传输请求
     *在没有传输参数，或者传输的是一个空字符串的情况下，都是用的是默认值user_name= 表示username=null传输的是一个空字符串
     *
     *
     * @RequestParam   @RequestHeader  @CookieValue三个注解默认的requied属性对应的默认值都是true,表示必须携带
     *
     */
    @GetMapping("/testParamsABC")
    public String testParamsABC(
            @RequestParam(value = "user_name",required = false,defaultValue = "haha") String username,   //表示将请求参数user_name与形参username对应
            String password,
            String hobby,
            /**
             * 默认情况下请求头是不会与控制器方法的形参之间建立映射关系的
             * 如果需要获取请求头中的信息，可以通过注解@RequestHeader()注解，该主机对应的属性值与@RequestParam一致
             */
            @RequestHeader(value = "sayhaha",required = true,defaultValue = "haha") String host,
            /**
             *因为所有的客户端浏览器的请求会携带cookie SessionID存放在Cookie
             * 可以通过@CookieValue将cookie与控制器方法的形参之间建立关系
             */
            @CookieValue(value = "JSESSIONID",required = true,defaultValue = "goldstine") String cookie
            ){
        System.out.println("通过控制器形参获取的请求参数"+username+password);
        System.out.println("获取的多个同名请求参数:");
//        System.out.println(Arrays.toString(hobby));
        System.out.println(hobby);

        System.out.println("对应的请求头信息："+host);

        System.out.println("获取的客户端额cookie:"+cookie);

        return "target";
    }


    //测试将请求参数与实体类之间参数映射

    /**
     * User{id=null, username='goldstine', password='2456789', sex='ç·', age=23, email='zs@qq.com'}
     * 可以通过实体类直接获取请求参数，只需要保证请求参数与实体类的属性名一致即可
     * 获取的id值为null,当插入数据库时会根据id生成策略获得对应的id
     *多个形参对象都会获得实体数据
     */
    @RequestMapping("/testBean")
    public String testBean(User user,User userInfo){

        System.out.println(user);//User{id=null, username='goldstine', password='2456789', sex='ç·', age=23, email='zs@qq.com'}

        System.out.println(userInfo);//User{id=null, username='goldstine', password='2456789', sex='ç·', age=23, email='zs@qq.com'}

        return "target";
    }

    /**
     * 中文乱码问题：
     * get请求的乱码是由tomcat服务器导致的，所以只需要在tomcat服务器的配置文件中配置，server.xml文件URIEncoding=UTF-8
     * 对于post请求的乱码问题:
     *  由于在请求参数之后进行参数编码设置没有用，所以应该在参数请求之前进行编码设置，又由于参数的获取是在DispatcherServlet中进行的
     *  由于客户端的请求，服务端三大组件的创建
     *  首先ServletContextListener最先加载，用于监听ServletContext对象的创建和销毁
     *  然后执行对应的过滤器的执行方法doFilter()
     *  最后再执行Servlet
     *  又因为监听器监听只执行一次
     *  所以应该通过过滤器进行编码设置
     *
     */

}
