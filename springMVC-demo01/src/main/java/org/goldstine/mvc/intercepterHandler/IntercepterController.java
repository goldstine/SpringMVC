package org.goldstine.mvc.intercepterHandler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IntercepterController {

    //创建拦截器
    /**
     * 拦截器：Intercepter对应的三个抽象方法
     * preHandler,PostHandler  AfterCompletion
     * preHandler在控制器方法之前进行执行
     * PostHandler在控制器方法之后执行
     * AfterCompletion在渲染视图之后执行
     *
     * 添加分页插件pageIntercepter,对应的分页插件就是通过拦截器进行功能添加
     *
     *
     * 默认情况下会将所有的请求进行拦截，所以需要对拦截器进行配置
     *
     */

    /**
     * SpringMVC的拦截器用于拦截控制器方法的执行
     * SpringMVC中的拦截器需要实现HandlerIntercepter
     * SpringMVC拦截器必须在SpringMVC配置文件进行配置
     */

    /**
     * 多个拦截器的执行顺序
     *perHandler按照配置文件中的配置顺序进行执行
     * postHandler和afterCompletion按照配置文件的反序进行执行
     *
     *
     */

    @RequestMapping("/**/testInterceptor")
    public String testInterceptor(){

        return "target";
    }


}
