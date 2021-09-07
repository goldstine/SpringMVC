package com.goldstine.servlet.cookie;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookieLoginWithoutUsername extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        //实现一个免用户名登录

        //首先获取用户名和密码，来自登录表单
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //判断用户名和密码是否正确，否者转回登录页，可以使用Filter  ,或者直接进行请求转发
        if("goldstine".equals(username)&&"123456".equals(password)){
            //如果正确，运行登录，将Cookie对象发送给客户端存储

            //创建一个Cookie对象发回客户端
            Cookie cookie = new Cookie("username", username);
            //可以设置Cookie对象的存活时间，有效路径
            cookie.setMaxAge(3600*24*7);//一周
            resp.addCookie(cookie);
            resp.getWriter().write("设置登录用户的Cookie对象创建成功");
        }else{
            //如果错误，请求转发
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }


    }
}
