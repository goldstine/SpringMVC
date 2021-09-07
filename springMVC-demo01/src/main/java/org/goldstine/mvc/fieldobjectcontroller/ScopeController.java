package org.goldstine.mvc.fieldobjectcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class ScopeController {


    //通过原生ServletAPI获取域对象的存取属性值

    @RequestMapping("/testRequestByServletApi")
    public String testRequestByServletApi(HttpServletRequest request){

        request.setAttribute("testRequestScope","liulei");

        return "target";
    }

    //通过SpringMVC框架的域对象共享数据获取
    /**
     * 使用ModelAndView向request域对象共享数据
     *
     */
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(){
        //首先创建一个ModelAndView对象
        ModelAndView mav = new ModelAndView();

        //往modelAndView对象中添加模型共享域对象的值
        mav.addObject("testRequestScope","hello,ModelAndView");
        //设置视图名称
        mav.setViewName("target");
        return mav;
    }

    //测试数据的Model共享域对象数据
    @RequestMapping("/testModel")
    public String testModel(Model model){
        model.addAttribute("testRequestScope","hello,Model");
        //输出对应的Model所属的类
        System.out.println("model所属的类为:"+model.getClass().getName());
        //model所属的类为:org.springframework.validation.support.BindingAwareModelMap
        System.out.println("model:"+model);
        return "target";
    }

    //使用map集合的方式向reuqest域对象共享数据
    @RequestMapping("/testMap")
    public String testMap(Map<String,Object> map){
        map.put("testRequestScope","通过map的方式共享域对象数据");
        System.out.println("map所属的类为："+map.getClass().getName());//获取的是真正进行实例化的类
        //map所属的类为：org.springframework.validation.support.BindingAwareModelMap
        System.out.println("map:"+map);
        return "target";
    }

    //通过ModelMap的方式实现域对象数据共享
    @RequestMapping("/testModelMap")
    public String testModelMap(ModelMap modelMap){
        modelMap.addAttribute("testRequestScope","通过ModelMap的方式共享域对象中的数据");
        System.out.println("ModelMap所属的类为："+modelMap.getClass().getName());
        //ModelMap所属的类为：org.springframework.validation.support.BindingAwareModelMap
        System.out.println("modelMap:"+modelMap);
        return "target";
    }


    /**
     * Model,Map,ModelMap三种方式共享数据的比较
     *public interface Model {
     * public class ModelMap extends LinkedHashMap<String, Object>
     *     public class ExtendedModelMap extends ModelMap implements Model
     *     public class BindingAwareModelMap extends ExtendedModelMap
     *
     *   所有的方式最终都是通过封装成ModelAndView对象
     */


    //通过session对象实现域对象数据共享

    /**
     * springmvc提供对应的将request对象的共享数据同步到session域对象，但是使用不方便
     * 所以直接使用参数赋值
     * @param session
     * @return
     */
    @RequestMapping("/testSession")
    public String testSession(HttpSession session){
        session.setAttribute("testRequestScope","通过session实现域对象数据共享");
        return "target";
    }


    //向application域对象共享数据
    @RequestMapping("/testApplication")
    public String testApplication(HttpSession session){

        //通过session获得servletContext对象，可以通过配置文件获得servletContext对象
        /**
         * 获取ServletContext对象的方式：
         * 通过jsp的pageContext对象
         * 通过session可以获得servletContext对象
         * 通过servlet的init(ServletConfig )对象获得servletContext对象
         */

        ServletContext sc = session.getServletContext();

        sc.setAttribute("testRequestScope","通过ServletContext对象共享域对象数据");

        return "target";
    }

}
