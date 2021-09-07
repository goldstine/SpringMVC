package com.goldstine.servlet.filter;

import javax.servlet.*;
import java.io.IOException;

public class Filter2 implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("Filter2前置代码.....");
        System.out.println("Filter2的执行线程为："+Thread.currentThread().getName());
        System.out.println("Filter2:"+request.getParameter("username"));
        System.out.println("Filter2中获取Filter1中保存的数据："+request.getAttribute("key1"));
        chain.doFilter(request,response);
        System.out.println("Filter2后置代码....");
    }

    @Override
    public void destroy() {

    }
}
