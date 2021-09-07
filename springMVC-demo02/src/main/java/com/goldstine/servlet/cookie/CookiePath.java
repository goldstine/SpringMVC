package com.goldstine.servlet.cookie;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookiePath extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //创建对应的Cookie对象时设置有效路径
        Cookie cookie = new Cookie("keyPath", "keyPathValue");
        //设置对应的有效路径 ,uri资源路径
//        cookie.setPath("/springMVC-demo02/admin");
        //可以直接通过req.getContextPath()获得工程路径
        cookie.setPath(req.getContextPath()+"/abc");//Path为   /工程路径/abc
        resp.addCookie(cookie);
        //设置对应的有效路径为
        resp.getWriter().write("设置有效路径为：/springMVC-demo02/admin");


        //将满足对应的地址，的Cookie对象发送给服务器端
    }
}
