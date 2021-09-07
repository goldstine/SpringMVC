package org.goldstine.servlet.ajax;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class GetInfo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //对于所有的

        System.out.println("点击了获取一些信息....");

        //正常情况下
        req.setAttribute("msg","<h1>hahahahhahah</h1>"+ UUID.randomUUID().toString());
        req.getRequestDispatcher("/index.jsp").forward(req,resp);
    }
}
