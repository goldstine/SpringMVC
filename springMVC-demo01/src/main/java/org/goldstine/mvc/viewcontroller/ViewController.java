package org.goldstine.mvc.viewcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    /**
     * SpringMVC中的视图是View接口，视图的作用是渲染数据，将Model中的数据展示给用户
     * SpringMVC视图的种类有很多
     * 默认有转发视图，重定向视图
     * 当工程引入jstl的依赖时，转发视图会自动转换为jstlView
     * 如使用的视图技术为thymeleaf，在springmvc的配置文件中配置了Thymeleaf的视图解析器之后，由此视图解析器解析之后得到的是ThymeleafView
     *
     * SpringMVC中默认的转发视图是InternalResourceView
     *重定向视图为RedirectView
     *
     *
     */

    /**
     * 对于没有处理逻辑的控制器组件，直接可以通过视图控制器进行设置View-Controller
     * @return
     */


    //首先实现页面跳转
//    @RequestMapping("/test_view")
//    public String testView(){
//        return "test_view";
//    }

    @RequestMapping("/testThymeleafView")
    public String testThymeleafView(){

        return "target";
    }


    //测试转发试图
    @RequestMapping("/testForward")
    public String testForward(){

        return "forward:/testThymeleafView";//首先会通过InternalResourceVire进行解析成转发试图，/testThymeleafView,
        //然后会通过对应的thymeleafView解析成thymeleafView视图
    }


    /**
     * 请求转发和重定向之间的区别：
     * （1）请求转发对于浏览器是同一次请求，所以地址栏不会变化  ，只不过在服务器内部进行转发
     * 重定向相当于浏览器发送了两次请求，浏览器地址不同
     * （2）请求转发，同一个请求，所以共享request域对象数据
     * （3）请求转发可以访问内部的WEB-INF文件夹，重定向不能访问
     * （4）重定向可以进行跨域访问www.baidu.com   请求转发在服务器内部不能够实现跨域
     *
     */

    /**
     * 通过forward请求转发视图的方式是首先通过IntenalResourceView视图进行解析，然后通过ThymeleafView视图解析
     * 通过redirect请求转发视图的方式是首先通过RedirectView视图进行解析，然后通过ThymeleafView视图解析
     * @return
     */

    //测试重定向试图
    //RedirectView
    @RequestMapping("/testRedirectView")
    public String testRedirectView(){
        return "redirect:/testThymeleafView";//http://localhost:8080/springMVC-demo01/testThymeleafView
    }

}
