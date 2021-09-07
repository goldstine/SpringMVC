package com.goldstine.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * servlet生命周期：
 * （1）首先执行servlet构造器方法
 * （2）执行servlet  init()
 * (3)执行service()
 * (4)执行destroy()
 *
 * 第（1）（2）在第一次访问的时候创建servlet对象的时候进行访问
 *（3）每次访问都会调用
 *（4）在web工程停止的时候进行调用
 */
public class HelloServlet implements Servlet {

    public HelloServlet() {
        System.out.println("servlet生命周期先执行构造器方法....");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("执行了对应的init....");

        /**
         * servletConfig的作用：
         * （1）获取对应的servlet别名servlet-name
         * (2)获取对应的初始化参数init-param
         * (3)获取ServletContext对象
         *
         */
        System.out.println("servlet别名："+ config.getServletName());
        System.out.println("初始化参数为:"+config.getInitParameter("username"));
        System.out.println("初始化参数："+config.getInitParameter("url"));
        //获得ServletContext对象
        ServletContext sc = config.getServletContext();
        System.out.println(sc);

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }
    //Service方法是专门用于处理和响应数据的
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("Hello Servlet被访问了。。。。");

        HttpServletRequest request = (HttpServletRequest) req;
        String method = request.getMethod();
//        System.out.println(method);
        if("GET".equals(method)){
            doGet();
        }else if("POST".equals(method)){
            doPost();
        }

    }

    public void doGet(){
        System.out.println("调用get请求");
    }

    public void doPost(){
        System.out.println("调用post请求");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("最终执行了destroy()");
    }
}
