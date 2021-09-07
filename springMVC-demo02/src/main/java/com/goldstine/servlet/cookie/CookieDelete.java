package com.goldstine.servlet.cookie;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookieDelete extends HttpServlet {

    //编写一个BaseServlet将所有的操作进行请求分发

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Cookie对象的删除，过期时间设置
        //首先需要获取对应的Cookie对象
//        Cookie[] cookies = req.getCookies();
//        Cookie newCookie = CookieUtils.findCookie("key1", cookies);
//        if(newCookie!=null) {
//            newCookie.setMaxAge(0);//单位是秒   默认就是-1，所以对应的Cookie对象，在关闭浏览器时删除
//            resp.addCookie(newCookie);//Set-Cookie: key1=asasas; Max-Age=0; Expires=Thu, 01-Jan-1970 00:00:10 GMT
//            //对应的修改存活时间也是通过响应头进行设置的Set-Cookie
//        }
//        resp.getWriter().write("key1的Cookie立即被删除。。。。");


        //创建一个指定存活时间的Cookie
        Cookie cookie = new Cookie("life3600", "life3600");
        //设置存活时间
        cookie.setMaxAge(3600);
        //将该cookie添加到http响应头
        resp.addCookie(cookie);//Set-Cookie: life3600=life3600; Max-Age=3600; Expires=Sun, 29-Aug-2021 05:16:45 GMT

        resp.getWriter().write("创建存活时间为3600秒的cookie对象");

    }
}
