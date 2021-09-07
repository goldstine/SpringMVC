package org.goldstine.mvc.jspcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JspController {
    //创建jsp的视图解析器

    //tomcat默认的配置文件中默认的访问页面为index.jsp   首先找index.html=>index.htm=》index.jsp
    //所以直接通过工程路径即可访问index.jsp   http://localhost:8080/springMVC-demo01/
    @RequestMapping("/success")
    public String testSuccess(){
        return "success";//没有前缀和后缀的视图名称会通过springMVC.xml中配置为的视图解析器进行解析
    }

    /**
     * 使用的对jsp进行解析的视图解析器是InternalResourceView视图解析器
     * 此时对于forward:/的方式的视图解析为对应的InternalResourceView  没有前缀和后缀的默认情况也是解析成对应的InternalResourceView
     *
     * 对于redirect:/的方式解析成redirectView视图，重定向视图
     *
     * 所以，不同的方式，使用的视图解析器不一样
     */

}
