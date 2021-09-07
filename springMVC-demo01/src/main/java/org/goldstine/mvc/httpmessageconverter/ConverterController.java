package org.goldstine.mvc.httpmessageconverter;

import org.goldstine.mvc.bean.User;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 复合注解：
 * @RestController
 * @RestController:注解时SpringMVC提供的一个复合注解，表示在控制器类上，就相当于为类添加了@Controller
 * 并且为其中的每一个方法都添加了@ResponseBody
 *
 */


@Controller
public class ConverterController {


    //配置当前测试页面的访问地址
    @RequestMapping("/converter")
    public String converterIndex(){
        return "converter";
    }

    /**
     * @RequestBody
     *String username,String password
     */
    @RequestMapping("/testRequestBody")
    public String testRequestBody(@RequestBody String str){

//        System.out.println("对应的请求参数为："+username+"password"+password);
        System.out.println("对应的请求报文转为对应的java对象"+str);//对应的请求报文转为对应的java对象username=gldstnie&password=123456
        System.out.println();
        //即通过@RequestBody接收的请求参数是对应的请求体

        return "target";

    }

    /**
     * RequestEntity封装请求报文的一种类型，将当前请求报文赋值给形参，可以通过getHeaders()方式获取请求头，通过getBody()获得请求体
     *
     */
    @RequestMapping("/testRequestEntity")
    public String testRequestEntity(RequestEntity<String> requestEntity){

        System.out.println("请求头为："+requestEntity.getHeaders());
        System.out.println("请求体为："+requestEntity.getBody());//get请求方式的请求体对应的为null,直接将对应的请求参数拼接在地址栏


        return "target";
    }

    /**
     * 控制对浏览器的响应:
     *  可以通过请求转发和重定向直接响应浏览器一个页面，或者通过响应浏览器一个数据流getWriter().write()
     *  前后端分离的项目比较多的是响应json数据
     * @ResponseBody
     *
     */

    //通过原生方式给浏览器响应数据
    @RequestMapping("/testResponse")
    public void testResponse(HttpServletResponse response) throws IOException {
        response.getWriter().write("通过原生servlet方式给浏览器相应一个数据");//跳转到/testResponse页面，输出对应的响应数据

//        return "target";
    }


    /**
     * 将返回值转换为json格式：
     * （1）首先导入依赖jackson
     * (2)在springmvc核心配置文件中开启mvc注解驱动，此时在HandlerAdapter中会自动装配一个消息转换器，MappingJackson2HttpMessageConverter
     *
     * 可以将响应的java对象转换为json格式的字符串
     * （3）在控制器方法上使用@ResponseBody注解进行标识
     * （4）将java对象作为控制器方法的返回值返回，就会自动转换为json格式的字符串
     *
     */

    @RequestMapping("/testResponseBody")
    @ResponseBody  //如果没有加上该注解在控制器方法上，对应的就是return "target"对应的视图解析器进行页面匹配
    //加上该注解之后，对应的就是相应的字符串，响应体
    public User testResponseBody(){
//        Map<String, Object> stringObjectHashMap = new HashMap<>();
//        stringObjectHashMap.put("username","liulei");
//        stringObjectHashMap.put("password","123456");
//        stringObjectHashMap.put("age",23);
        User user = new User(12, "goldstnie", "12345", "nan", 23, "zs@qq.cmo");

        return user;

//        return "taregt";
    }


    //测试使用ajax
    @RequestMapping("/testAjax")
    @ResponseBody
    public String testAjax(String username,String password){
        System.out.println("请求参数为"+username+"password"+password);
        return "here is string";
    }


    /**
     * ResponseEntity       应用于实现文件上传下载
     * 用于控制器方法的返回值类型，该控制器方法的返回值就是响应到浏览器的响应报文
     *
     */



}
