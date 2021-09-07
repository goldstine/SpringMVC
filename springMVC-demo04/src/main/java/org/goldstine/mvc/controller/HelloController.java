package org.goldstine.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 通常创建的控制器类所在的包，也可以称为Handler,springMVC源码更多的是叫做handler
 * 前端控制器只是将请求参数进行了处理，具体的处理还需要对应的处理类，即请求控制器
 * 请求控制器中每一个处理请求的方法称为控制器方法
 *配置作为spring容器中的组件的方式有两种方式;(1)通过spring配置文件 bean标签（2）通过注解+扫描
 * 通过@Component表示为一个普通组件  @Controller控制层组件   @Service表示为业务层组件  @Repository持久层组件
 *
 */
@Controller
public class HelloController {

    // "/"--->/WEB-INF/templates/index.html
    //实际处理请求的类，将对应的除去视图前缀和视图后缀的视图名称返回给视图解析器进行页面的跳转
    //通过注解@RequestMapping将当前的请求和控制器方法之间创建映射关系
    @RequestMapping(value = "/")
    public String index(){
        //返回视图名称
        return "index";//通过视图解析器加上前缀和后缀就可以实现页面的跳转
    }
}
