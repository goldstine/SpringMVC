package org.goldstine.mvc.intercepterHandler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionHandler {


    //定义一个异常请求
    @RequestMapping("/testException")
    public String testExcpetion(){
        int i=10/0;
        return "target";
    }
}
