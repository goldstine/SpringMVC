package com.goldstine.servlet.dispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ForwardC extends HttpServlet {

    /**
     * 所有相对路径在工作时候都会参照当前浏览器地址栏中的地址来进行跳转
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("通过请求转发程序forwardC");
        // /斜杠表示 http://ip:port/工程名/
        req.getRequestDispatcher("/a/b/c.html").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }
}
