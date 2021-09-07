package com.goldstine.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ContextServlet2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //在另一个servlet中获取其它servlet往域对象ContextServlet对象中存入的数据
        //因为一个web工程之后一个ServletContext对象，所以不同servlet程序获取的servletContext对象是同一个
        ServletContext servletContext = getServletContext();

        //打印该对象地址，和其他servlet中的该对象地址相同，为同一个对象
        System.out.println(servletContext);

        System.out.println("其它servletcontext存储的数据："+servletContext.getAttribute("key1"));
        //每次重启服务器就会将该数据销毁，所以需要重新创建
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }
}
