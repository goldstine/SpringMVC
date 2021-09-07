package org.goldstine.servlet.ajax;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //通过对应的发送给客户端响应数据
        resp.setContentType("text/html;charset=utf-8");

        System.out.println("服务器端响应客户端数据....");

        resp.getWriter().write("<h1>我是中国人</h1>");

    }
}
