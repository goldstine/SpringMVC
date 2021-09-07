package com.goldstine.servlet.dispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收来自servlet1的请求
        //检查对应的证件信息
        String username = req.getParameter("username");
        System.out.println("servlet2 username:"+username);
        //检查servlet1携带的数据 ，由于请求转发对应的域对象是同一个对象，
        Object key1 = req.getAttribute("key1");
        System.out.println("域对象的值为："+key1);
        //对浏览器请求响应
        System.out.println("将请求相应浏览器");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }
}
