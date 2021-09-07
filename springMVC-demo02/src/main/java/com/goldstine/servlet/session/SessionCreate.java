package com.goldstine.servlet.session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 所有的域对象：HttpServletRequest   HttpServletResponse   ServletContext    Session
 */
public class SessionCreate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        //创建session
        HttpSession session = req.getSession();//Set-Cookie: JSESSIONID=F800EE66CF898246E7917D049D937FAC; Path=/springMVC-demo02; HttpOnly
        //判断当前session是否是新创建的
        boolean isNew = session.isNew();
        //获取session会话的唯一标识
        String id = session.getId();

        resp.getWriter().write("得到的Session，它的id是:"+id+"<br/>");
        resp.getWriter().write("这个session是否是新创建的:"+isNew+"<br/>");


        //session作为域对象存储数据
        session.setAttribute("key1","value1");



    }
}
