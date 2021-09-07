package org.goldstine.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(
            value = {"/target","/test"},
            method={RequestMethod.GET}
    )
    public String toTarget(){
        return "target";
    }

    /**
     * 对应的RequestMapping的属性值：
     * value[] 通过请求路径进行匹配，可以有多个请求路径映射到一个控制器进行处理,至少需要对value[]属性进行配置
     * 通过method请求方式进行匹配：method同样也是一个字符串数组，可以设置多种请求方式，数据类型是RequestMethod枚举类中的属性值
     *如果不对method[]属性进行设置，表示不对请求的类型进行过滤，get/post类型的请求都会发送到对应的控制器组件进行处理
     *如果请求方式不支持的话，浏览器请求出现对应的405错误
     *
     */
    /**
     * 对于处理指定请求的方式的控制器方法，SpringMVC中提供了@RequestMapping注解的派生注解
     * 处理get请求的映射===》@GetMapping
     * 处理post请求的映射===.@PostMapping
     * 处理put请求的映射===》@PutMapping
     * 处理delete请求的映射===》@DeleteMapping
     * 目前浏览器只支持get post，若在form表单提交时为method设置了其他请求方式的字符串（put/delete）则按照默认的方式请求get处理
     * 若要发送put和delete请求，则需要通过spring提供的过滤器HiddenHttpMethodFilter,在restful
     *
     *通过查连接的方式发送的请求是get请求
     *
     * @
     */

    //测试一下put请求，浏览器只能发送get请求
    @PutMapping("/testPutMapping")
    public String testPutMapping(){//浏览器直接显示405请求错误，所以是浏览器发送get请求，但是服务器需要处理的是put请求
        // Request method 'GET' not supported   浏览器会将请求转换为对应的get请求
        return "index";
    }


    /**
     * parmas属性的请求设置，
     * params={!username}  表示请求必须不能携带参数username
     * params={username}        表示请求必须需要携带对应的请求参数username
     * params={username=admin}  表示请求必须有请求参数username 且该参数必须对应的值为admin  username=admin
     * params={username!=admin}     表示请求参数必须要username 但是该值不能是admin
     *
     */
    @GetMapping(
            value = "/testParams",
            params = {"username=admin","!password"}//如果不满足对应的参数设置，会出现400错误
    )
    public String testParams(){
        return "target";
    }

    //测试对应的header请求匹配
    @GetMapping(
            value = {"/testHeader"},
            headers = {"Host=localhost:8081"}//404   请求头属性的设置也是对应的k:v设置，所以设置方式与上面的params设置的4种表达式一直
            /**
             * {"Host"}表示请求头中需要对应的Host字段
             * {"!Host"}表示请求头中不需要对应的Host字段
             * {"Host=localhost:8080"}表示请求头中需要对应的字段，并且对应的值必须为localhosthost：8080
             * {"Host!=localHost:8080"}  表示对应的请求头种对应的字段需要Host,但是值不能是localhost:8080
             *
            */
    )
    public String testHeader(){
        return "index";
    }
    /**
     * SpringMVC支持ant风格的路径
     *  ?表示可以匹配任意单个字符
     *  *表示任意的0个或多个字符
     *  **:表示任意一层或多层目录  ，
     */
    //注意：在使用**的时候，只能使用/**/xxx的方式，否者当成一个*表示0个或多个字符
//    @GetMapping("/a?a/testAnt")
//    @GetMapping("/a*a/testAnt")
//    @GetMapping("/a**a/testAnt")当成一个*表示0个或多个字符
    @GetMapping("/**/testAnt")//表示一个或多个目录
    public String testAnt(){
        return "target";
    }


    /**
     * SpringMVC支持路径中的占位符
     * 原始方式：/deleteUser?id=1
     * rest方式：/deleteUser/1
     * SpringMVC中的占位符常用于restful风格中，当请求路径中将
     */
    @GetMapping(
            value = {"/testRest/{id}"}//rest风格的参数接收，通过占位符{}名称为id对参数进行接收
            //当设置rest风格的占位符的时候，浏览器中的地址必须传递对应的值，否者浏览器报404错误
    )
    public String testRest(@PathVariable("id") Integer id ){
        System.out.println(id);
        return "target";
    }

}
