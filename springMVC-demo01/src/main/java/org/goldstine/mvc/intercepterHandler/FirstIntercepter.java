package org.goldstine.mvc.intercepterHandler;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 创建一个intercepter的方式：（1）实现接口HandlerIntercepter  (2)继承类HandlerIntercepter
 */
@Component
public class FirstIntercepter implements HandlerInterceptor {

    //HandlerIntercepter提供三个默认方法
    //通过ctrl+O重写方法
    //要是拦截器发挥作用，需要在配置文件中配置
    //DispatcherServlet在执行之后调用所有的拦截器进行执行

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("执行preHandler");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("执行postHandler");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("执行afterCompletion...");
    }
}
