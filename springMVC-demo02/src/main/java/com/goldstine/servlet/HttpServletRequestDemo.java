package com.goldstine.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class HttpServletRequestDemo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * public String getRequestURI();           获取请求的资源路径
         *  public StringBuffer getRequestURL();    获取请求的统一资源定位符【绝对路径】
         *  public String getRemoteHost();      在服务器端获取客户端的ip
         *  public String getHeader(String name);   获取请求头
         *  getParameter()                          获取请求参数
         *  getParameterValues()                    获取请求参数（多个值的时候使用）
         *   public String getMethod();             获取请求方式get post,实现请求分发的时候用过
         *   setAttribute(key,value)                设置域数据  通过servletContext对象也可以
         *   getAttribute(key)                      获取域数据
         *   getRquestDispatcher()                  获取请求转发对象
         */

        //首先直接通过httpServletRequst获取数据
        String requestURI = req.getRequestURI();
        System.out.println("请求的资源路径："+requestURI);
        System.out.println("请求的绝对路径是:"+req.getRequestURL());
        //获取远程客户端ip
        System.out.println(req.getRemoteHost());
        //获取请求头
        System.out.println(req.getHeader("User-Agent"));


        //浏览器表单数据的接收        获取请求的参数
        //浏览器的请求：http://localhost:8080/springMVC-demo02/goldstine?username=goldstine&password=31212121&hobby=java
        String username = req.getParameter("username");
        String password = req.getParameter("password");
//        String hobby = req.getParameter("hobby");
        //对兴趣爱好有多个值返回，使用getParameters()
        String[] hobbies = req.getParameterValues("hobby");

        System.out.println("用户名"+username);
        System.out.println("密码："+password);
//        System.out.println("兴趣爱好："+hobby);

        //数组和集合都可以生成对应的stream()
        for (String hobby : hobbies) {
            System.out.println(hobby);
        }
        //遍历方式，将数组转为集合进行遍历  集合有很多种遍历方式（1）for (2)迭代器 （3）foreach  (4)lambda
        System.out.println(Arrays.toString(hobbies));


        //设置请求体的字符集为utf-8，从而解决post请求的中文乱码问题
        req.setCharacterEncoding("UTF-8");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /**
         * 对于表单采用post请求进行提交会出现获取参数乱码的问题，需要通过HttpServletRequest对象设置对应的编码方式
         * setCharacterEncoding()必须在获取参数之前进行设置，否则还是会出现乱码问题
         *
         */
        //设置请求体的字符集为utf-8，从而解决post请求的中文乱码问题
        req.setCharacterEncoding("UTF-8");

       //如果没有删除调用父类的方法，浏览器会出现405错误
        String username = req.getParameter("username");
        String password = req.getParameter("password");
//        String hobby = req.getParameter("hobby");
        //对兴趣爱好有多个值返回，使用getParameters()
        String[] hobbies = req.getParameterValues("hobby");

        System.out.println("用户名"+username);
        System.out.println("密码："+password);
//        System.out.println("兴趣爱好："+hobby);

        //数组和集合都可以生成对应的stream()
        for (String hobby : hobbies) {
            System.out.println(hobby);
        }
        //遍历方式，将数组转为集合进行遍历  集合有很多种遍历方式（1）for (2)迭代器 （3）foreach  (4)lambda
        System.out.println(Arrays.toString(hobbies));

    }
}
