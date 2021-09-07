package org.goldstine.servlet.ajax;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AjaxServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");

        //获取参数数据
        String lastName = req.getParameter("lastName");
        String age = req.getParameter("age");
        System.out.println("获取到的值："+lastName+"======"+age);

        Map<String,Object> map=new HashMap<>();
        map.put("lastName",18);
        map.put("age",18);
        //1.转成json
        Gson gson = new Gson();
        String json = gson.toJson(map);
        resp.getWriter().write(json);

    }
}
