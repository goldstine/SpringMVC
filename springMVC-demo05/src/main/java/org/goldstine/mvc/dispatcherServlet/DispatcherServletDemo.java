package org.goldstine.mvc.dispatcherServlet;

import javax.servlet.Servlet;

public class DispatcherServletDemo {
    /**
     * SpringMVC对应的组件：
     * （1）DispatcherServlet:
     * （2）HandlerMapping
     * (3)Handler
     * (4)HnalderAdapter
     * (5)viewresolver
     * (6)view:对应的就是一个一个的视图
     */

    /**
     * DispatcherServlet的初始化过程：作为一个Servlet首先应该是执行servlet的初始化
     *
     */

    /**
     * 对于一次前端请求，服务器端的处理过程
     *  （1）首先通过dispatcherServlet前端控制器接收响应的请求，通过前端控制器获取对应的URI资源路径
     *  （2）如果配置类默认的servlet，则通过默认的servlet进行处理  default-servlet-handler
     *  (3)处理完之后调用handlerMapping获得请求与处理器handler之间的映射关系
     *  （4）然后获得调用控制器的合适的handlerAdapter
     *  (5)然后调用拦截器方法执行preHandler
     *  (6)如果拦截器请求返回true，执行对应的处理器参数初始化
     *  （7）执行处理器handler,数据处理，将返回的数据通过ModelAndView共享到域对象中
     *  （8）控制器执行完之后通常返回对应的视图
     *  （9）然后执行posthandler,[如果有多个拦截器的话，继续执行下一个拦截器的prehandler]
     *  （10）再将数据ModelAndView通过视图解析器进行解析，如果是没有前缀的视图，将解析成为thymeleafView
     *  如果为转发试图，解析成为InternalResourceView 如果是重定向视图解析成为RedirectView
     *  (11)解析完成试图返回浏览器对应的视图，否者出现异常
     */

}
