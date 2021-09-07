package com.goldstine.servlet.httpservletresponse;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class ResponseIOServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //设置响应数据的编码方式
//        resp.setCharacterEncoding("UTF-8");//这里只是设置了服务器的字符编码方式，还需要设置对应的浏览器的字符编码方式
//        //通过程序设置浏览器编码方式
//        resp.setHeader("Content-Type","text/html;charset=UTF-8");

        //可以通过一个方法同时设置服务端的编码方式，客户端的编码方式一个响应头
        resp.setContentType("text/html;charset=UTF-8");//此方法必须在获取流之前进行调用

        //验证响应对象可以获得两个输出流getOutputStream()    getWriter()
        //但是两个输出流只能使用一个

//        OutputStream os = resp.getOutputStream();
//        os.write('a');
//        os.write('我');
        //将该字节流进行包装，或者直接使用工具

        //获得Writer字符流
        PrintWriter writer = resp.getWriter();

        //默认响应字符编码
        System.out.println(resp.getCharacterEncoding());
        //将字符进行
        writer.write("我是中国人");//默认情况下中文会出现乱码，默认使用的响应编码方式为ISO-8859-1

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }
}
