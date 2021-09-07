package com.goldstine.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ContextServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //验证ServletContext的作用
        //首先通过ServletConfig对象获得对应的ServletContext对象
//        ServletConfig servletConfig = getServletConfig();
//        ServletContext servletContext = servletConfig.getServletContext();

        //可以直接通过getServletContext()获得对应的ServletContext对象，该方法在GenericServlet类中被定义
        ServletContext servletContext = getServletContext();

        //1、获取web.xml配置的上下文参数    通过getInitParam()方法获取上下文参数
        String username = servletContext.getInitParameter("username");
        String password = servletContext.getInitParameter("password");
        System.out.println("username"+username+"========="+"password:"+password);
        System.out.println(servletContext.getInitParameter("url"));//不能得到其它servlet的init-param
        System.out.println(servletContext.getInitParameter("contextParam"));//当前自己的init-param也不能获得

        //2、获取当前的工程路径
        String contextPath = servletContext.getContextPath();
        System.out.println(contextPath);
        //获取部署之后服务器上的绝对路径
        String realPath = servletContext.getRealPath("/");// /斜杠表示服务器解析地址为http://ip:port/工程名/
        System.out.println("工程部署的路径是："+realPath);//其实映射的是工程对应的webapp

        System.out.println("工程部署的css目录路径是："+servletContext.getRealPath("/css"));

        //ServletContext域对象可以向map一样存储数据
        //往servletContext对象中存入数据
        servletContext.setAttribute("key1","value1");
        System.out.println("context中获取域数据key1的值是："+servletContext.getAttribute("key1"));

        //打印servletContext对象地址
        System.out.println(servletContext);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
