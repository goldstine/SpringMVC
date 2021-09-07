package org.goldstine.mvc.config;

import org.goldstine.mvc.interceptor.TestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import java.util.List;
import java.util.Properties;


@Configuration//将当前类表示为一个配置类
@ComponentScan("org.goldstine.mvc.controller")      //通过配置扫描的包表示需要扫描的包
@EnableWebMvc        //开启webmvc的注解驱动
public class WebConfig implements WebMvcConfigurer {

    //是否记得对应的通用权限管理系统的，所有web层的组件都需要在对应的WebMvcConfigurer接口中进行配置
    //比如默认的servlet  default-servlet-handler   拦截器  Intercepter

    /**
     * 代替Springmvc的配置文件
     * (1)扫描组件  （2）视图解析器  （3）view-controller  (4)default-servlet-handler
     * (5)mvc注解驱动   （6）文件上传解析器  （7）异常处理   （8）拦截器
     */

    //通过接口的方式配置default-servlet-handler


    //配置生成模板解析器
    @Bean
    public ITemplateResolver templateResolver() {
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        // ServletContextTemplateResolver需要一个ServletContext作为构造参数，可通过WebApplicationContext 的方法获得
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(
                webApplicationContext.getServletContext());
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        return templateResolver;
    }

    //生成模板引擎并为模板引擎注入模板解析器
    @Bean
    public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }

    //生成视图解析器并未解析器注入模板引擎
    @Bean
    public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setTemplateEngine(templateEngine);
        return viewResolver;
    }

//    default-servlet-handler
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        //通过接口的形式进行配置默认的Servlet，然后实现里面的方法
        configurer.enable();//开启默认的servlet
    }

    //通过接口WebMvcConfigurer配置拦截器

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        TestInterceptor testInterceptor = new TestInterceptor();
        //对拦截器进行规则配置
//添加一个拦截器，所以需要创建一个拦截器，实现HandlerIntercepter接口，或者继承
        registry.addInterceptor(testInterceptor).addPathPatterns("/**");
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //对视图控制器进行配置   View-Controller
        registry.addViewController("/hello").setViewName("hello");
    }

    //配置文件上传解析器
    //通过bean的方式进行配置
    @Bean
    public MultipartResolver mutilpartResovler(){
        //创建MutilpartResolver接口的实现类创建的对象返回
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        return commonsMultipartResolver;
    }

    //通过web接口的方式进行异常处理的配置

    /**
     * 异常处理的配置在对应的springmvc.xml文件中是通过bean的形式进行配置的
     * 但是对应的webMvcConfigurer接口中已经提供了对应实现类，所以直接通过实现接口中的方法完成配置
     * 但是也可以通过bean的方式进行配置
     * 所以对于异常解析器的配置可以通过bean的方式配置也可以通过接口的方式配置
     * @param resolvers
     */
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        //参数是一个集合，所以创建对应的HandlerExceptionResolver对象，添加到集合中
        SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();

        //通过Proerties的形式进行配置，所以创建对应的对象
        Properties props = new Properties();

//        props.put() ====>由于properties对应的配置文件自能为String类型，所以提供对应的方法进行数据的存取
        //setProperty(String key,String value)   getProperty(String key,String value)
        props.setProperty("java.lang.ArithmeticException","error");
        //将SimpleMappingExceptionResolver对象的引用对象属性赋值为Props
        simpleMappingExceptionResolver.setExceptionMappings(props);

        //配置请求与对象的携带共享数据,只需要设置键
        simpleMappingExceptionResolver.setExceptionAttribute("exception");

        resolvers.add(simpleMappingExceptionResolver);
    }
}
