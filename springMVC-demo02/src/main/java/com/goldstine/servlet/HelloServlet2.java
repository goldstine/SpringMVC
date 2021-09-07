package com.goldstine.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 通过继承Servlet接口的子类实现Servlet程序
 * 然后重写对应的doGet()  doPost()
 * 不同请求的分发通过HttpServlet进行了实现  get post put delete trace等等http协议
 */
public class HelloServlet2 extends HttpServlet {

    //如果重写init()方法一定要显式调用父类的构造方法


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

    }

    /**
     * 当接收get请求的时候调用
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("helloServlet2 的doGet方法");
        ServletConfig sc = getServletConfig();//可以在其他地方获得对应的ServletConfig对象，但是不能够获取其他的servlet程序的初始化参数和servlet别名
        System.out.println(sc);
        System.out.println(sc.getInitParameter("username"));//不能够获取其它servlet程序配置的参数数据
        System.out.println(sc.getInitParameter("password"));//可以获取自己的servlet程序配置的参数
        System.out.println(sc.getServletName());

    }

    /**
     * 当接收post请求时调用
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("helloServlet2的 doPost方法");
    }
}
