package org.goldstine.mvc.intercepterHandler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 在servlet3.0环境中，容器会在类路径中查找实现javax.servlet.ServletContainerInitializer接口的类,如果找到的话就是用它来配置Servlet容器
 * Spring提供了这个接口的实现，名为SpringServletContainerInitializer，这个类反过来又会查找实现
 * WebApplicationInitializer的类并将配置的任务交给它们来完成。
 * spring3.2引入了一个便利的WebApplicationInitialzier，名为AbstractAnnotationConfigDispatcherServletInitializer
 *当我们的类扩展了AbstractAnnotationConfigDispatcherServletInitializer并将其部署到servlet3.0容器的时候，容器会自动发现他，并用它来配置servlet上下文
 *
 * 所以只需将AbstractAnnotationConfigDispatcherServletInitializer进行继承，也就是继承了web.xml
 *
 */



@ControllerAdvice   //@ControllerAdvice注解是基于@Component注解的基础上扩展的注解
public class ExceptionAnnotation {
    /**
     * 基于注解的方式定义异常处理，其实和基于配置的方式定义异常一致
     * (1)指定可能发生的异常名，
     * （2）指定发生异常之后跳转的页面
     * （3）将请求域中的数据保存使用的键
     */
    @ExceptionHandler(value = {ArithmeticException.class,ClassNotFoundException.class})
    @RequestMapping("/testExceptionAnnotation")
    public String testExceptionAnnotation(Exception ex, Model model){
//        request.setAttribute("exceptionAttribute",ex);
        model.addAttribute("ex",ex);
        return "error";
    }
}
