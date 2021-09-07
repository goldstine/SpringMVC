package com.goldstine.servlet.requestredirect;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 请求重定向特点：
 * （1）浏览器地址栏会发生变化
 * （2）属于浏览器不同的请求
 * （3）不共享域对象 HttpServletRequest
 * (4)不能够访问WEB-INF目录
 * （5）可以访问工程外的资源，重定向到www.baidu.com
 */
public class Response1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 请求重定向，浏览器首先访问服务端，服务端将应该访问的地址返回给客户端浏览器
         * 返回两个数据（1）状态码302表示重定向  （2）再响应头中返回对应的新的地址
         *
         */
        System.out.println("曾到此一游 Response1");

        //设置响应码302
//        resp.setStatus(302);
//
//        //设置响应头
//        resp.setHeader("Location","http://localhost:8080/springMVC-demo02/response2");


        //请求重定向的第二种方式,只需要通过一个方法,由于302状态码是固定的，所以只需要给出重定向的地址即可
        resp.sendRedirect("http://www.baidu.com");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }
}
