package com.goldstine.servlet.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListenerImpl implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("servletContext对象被创建了.....");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("servletContext对象被销毁了.....");
    }
}
