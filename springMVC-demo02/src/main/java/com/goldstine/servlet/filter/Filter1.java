package com.goldstine.servlet.filter;

import javax.servlet.*;
import java.io.IOException;

public class Filter1 implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("Filter1前置代码.....");
        System.out.println("Filter1的执行线程为："+Thread.currentThread().getName());
        System.out.println("Filter1:"+request.getParameter("username"));
        //在Filter1中保存数据
        request.setAttribute("key1","liulei");
        chain.doFilter(request,response);
        System.out.println("Filter1后置代码.....");
    }

    @Override
    public void destroy() {

    }
}
