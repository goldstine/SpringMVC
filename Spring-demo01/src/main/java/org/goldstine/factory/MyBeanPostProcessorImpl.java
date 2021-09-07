package org.goldstine.factory;

import org.goldstine.bean.Car;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 创建接口Bean后置处理器  BeanPostProcessor
 *Object bean:将要初始化的bean
 *
 *
 * （1）编写后置处理器的实现类
 * （2）将后置处理器注册在配置文件中
 */
public class MyBeanPostProcessorImpl implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("["+beanName+"]bean将要调用初始化方法，这个bean是这样的:"+bean);
        //返回传入的bean

        return bean;
    }

    /**
     *postProcessAfterInitialization:在初始化方法之后进行调用
     *Object bean;
     * String beanName:bean在xml中配置的id
     *
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("bean初始化方法调用完毕，。。。。。");
        //初始化之后返回的bean,返回的是什么，容器中保存的就是什么
        return bean;
    }
}
