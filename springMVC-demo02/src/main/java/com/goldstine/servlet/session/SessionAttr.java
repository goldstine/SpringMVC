package com.goldstine.servlet.session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Session超时时间是客户端两次请求之间的最大时间间隔，超过该时间间隔之后的客户端请求
 * 都会重新创建一个新的session
 */
public class SessionAttr extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        //获取session域对象的属性值
        HttpSession session = req.getSession();
        //获取key1的值
        System.out.println(session.getAttribute("key1"));//session在所有的servlet程序之间共享同一个


        int maxInactiveInterval = session.getMaxInactiveInterval();
        System.out.println(maxInactiveInterval);//1800,session的默认超时时间为30min

        //设置session的超时时间为20min
        session.setMaxInactiveInterval(10);
        resp.getWriter().write("对应的sessionId:"+session.getId());
        System.out.println("通过api设置的对应的session超时时间为："+session.getMaxInactiveInterval());


        //设置session对象立刻超时失效
//        session.invalidate();

    }
}
