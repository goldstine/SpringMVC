package com.goldstine.servlet.cookie;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 所有的修改Cookie对象的值，都是在响应头中设置一个Set-Cookie字段进行的
 * 注意：不管通过何种方式，对应的Cookie对象的值都不支持中文
 * 如果需要使用特殊字符，需要使用Base64编码之后，作为Cookie值，所以Base64编码方式的作用之一：特殊字符编码转换
 */

public class CookieUpdate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //对客户端保存的cookie值进行修改,其实就是将原先的cookie通过key进行覆盖
//        Cookie cookie = new Cookie("key1", "goldstine");
//        //将cookie添加到响应头中
//        resp.addCookie(cookie);
//
//        //输出修改cookie成功
//        resp.getWriter().write("修改cookie成功！！！");

        /**
         * 修改方式二：
         * 原理是存放在浏览器客户端的cookie是通过Map,无重复，无序，无索引
         * 所以方式一，将一个相同key的cookie对象传输给客户端，会将客户端的cookie对象覆盖
         * 所以方式二，也可以通过setValue()使用同一个cookie对象传输给客户端覆盖原先的cookie
         */
        //方式二
        //获取cookie数组中的cookie对象，通过setValue()修改cookie对象值，然后继续传输给客户端
        Cookie[] cookies = req.getCookies();

        //找到对应的cookie对象
        Cookie newCookie = CookieUtils.findCookie("key1", cookies);
        if(newCookie!=null) {
            newCookie.setValue("asasas");
            //再将该Cookie对象传输给客户端
            resp.addCookie(newCookie);
        }
        resp.getWriter().write("通过方式二修改Cookie对象值");

    }
}
