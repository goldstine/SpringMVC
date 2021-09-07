package com.goldstine.servlet.filter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //通过登录，将用户的登录信息存储到对应的session对象中
        //也是通过HttpServletRequest对象获得session将，数据存储到该HttpSession对象中

        resp.setContentType("text/html;charset=UTF-8");

        //将表单提交的用户登录信息进行获取参数
        String username = req.getParameter("username");//name对应的表单的name值
        String password = req.getParameter("password");

        if("goldstine".equals(username)&&"123456".equals(password)){
            req.getSession().setAttribute("user",username);

            resp.getWriter().write("登陆成功！！！！！");
        }else{
            //如果登陆失败，继续请求转发到登陆页面
            req.getRequestDispatcher("/admin/login.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }
}
