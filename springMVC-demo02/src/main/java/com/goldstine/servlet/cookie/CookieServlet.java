package com.goldstine.servlet.cookie;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookieServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setContentType("text/html;charset=UTF-8");
        //1、创建Cookie对象
        Cookie cookie = new Cookie("key2", "value2");
        //2、通知客户端保存Cookie
        resp.addCookie(cookie);//将cookie添加到对应的响应流中
        /**
         * Set-Cookie: key2=value2
         * Set-Cookie: key3=value3   通过addCookie()会在对应的响应头中创建对应的Set-Cookie字段，以键值对的形式创建
         */
        //可以一次创建多个cookie
        Cookie cookie1 = new Cookie("key3", "value3");
        resp.addCookie(cookie1);


        //将响应流输出给客户端
        resp.getWriter().write("Cookie创建成功！！！");

        //服务器如何获取cookie，cookie保存在客户端，每一次请求都会将cookie发送给服务器，服务器如何获取对应的cookie
        //服务器获取客户端的Cookie只需要一行代码 req.getCookies():Cookie[]  然后返回对应的cookie数组
        //客户端在请求的时候会在对应的请求头上加上对应的cookie字段
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie2 : cookies) {
            //getName()返回Cookie的key(名)
            //getValue()返回对应的值
            resp.getWriter().write("Cookie["+cookie2.getName()+"="+cookie2.getValue()+"] <br/>");
        }

        //由于没有提供通过key查找对应的cookie的方法
        //所以只能通过遍历所有的cookie数组进行对应的cookie查找
//        Cookie iWantCookie=null;
//        for (Cookie cookie2 : cookies) {
//            if("key2".equals(cookie2.getName())){
//                iWantCookie=cookie2;
//                break;
//            }
//        }

        Cookie iWantCookie=CookieUtils.findCookie("key1",cookies);

        //如果不为null，说明赋过值，也就是找到了对应的cookie
        if(iWantCookie!=null){
            resp.getWriter().write("找到了对应的cookie!!!");
        }
    }
}
