package com.goldstine.servlet.dispatcher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet1 extends HttpServlet {
    /**
     * 请求转发的特点：
     * （1）该请求都属于通过一个请求
     * （2）域对象属于同一个与对象
     * （3）请求转发可以访问WEB-INF目录
     * （4）浏览器地址栏不会发生变化
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //对于请求转发流程

        //1、首先检查请求的参数，证件信息
        String username = req.getParameter("username");
        System.out.println("用户信息为:"+username);
        //2、给请求加上附加信息 盖上章
        //通过HttpServletRequest设置对应的域属性
        req.setAttribute("key1","value1Goldstine");
        //3、询问下一个地址
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/servlet2");

        //4、将请求转发到对应位置
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }
}
