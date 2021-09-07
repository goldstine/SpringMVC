package com.goldstine.servlet.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminFilter implements Filter {

    //构造器方法
    public AdminFilter(){
        System.out.println("1.Filter构造器方法AdminFilter()");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("2.Filter  init方法");

        /**
         * 对应的FilterConfig都在对应的初始化方法中，默认启动web工程时调用
         */
        //fitlerConfig作用：
        //获取filter的名称
        String filterName = filterConfig.getFilterName();
        System.out.println("filter的名称："+filterName);
        //获取初始化参数
        String username = filterConfig.getInitParameter("username");
        System.out.println("初始化参数为："+username);
        //获取servletContext对象
        ServletContext servletContext = filterConfig.getServletContext();
        System.out.println(servletContext);
    }

    /**
     * doFilter()方法，专门用于拦截请求，可以做权限检查
     * 处理完对应的处理逻辑之后，还需要在web.xml文件中进行配置
     *
     * 对于图片的拦截可能会出现浏览器缓存的问题，如果浏览器有对应的内容缓存，则直接获取，不会请求服务器
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("Filter的doFilter()方法");

        //通过HttpServletRequest获取HttpSersion对象
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();

        Object user = session.getAttribute("user");
        if(user==null){
            //请求转发  ,这里请求转发的路径是 资源路径/admin/jsp  原因是对应的servlet程序也是资源，只不过这种资源需要在web.xml文件中配置路径
            httpServletRequest.getRequestDispatcher("/admin/login.jsp").forward(request,response);
            return;
        }else{
            //让程序继续往下访问用户的目标资源
            chain.doFilter(request,response);
        }

    }

    @Override
    public void destroy() {
        System.out.println("Filter的destory()方法执行");
    }
}
