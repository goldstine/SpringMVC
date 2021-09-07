package org.goldstine.mvc.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;


//作为一个替代web.xml配置文件的类
public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * 指定Spring的配置
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
//        return new Class[0];
        //将配置类放在数组中返回
        return new Class[]{SpringConfig.class};
    }

    /**
     * 指定SpringMVC的配置
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
//        return new Class[0];
        return new Class[]{WebConfig.class};
    }

    /**
     * 指定DispatcherServlet的url-pattern配置
     * @return
     */
    @Override
    protected String[] getServletMappings() {
//        return new String[0];
        return new String[]{"/"};
    }

    /**
     * 注册过滤器
     * @return
     */
    @Override
    protected Filter[] getServletFilters() {
        //创建对应的过滤器对象
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceResponseEncoding(true);
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        return new Filter[]{characterEncodingFilter,hiddenHttpMethodFilter};
    }
}
