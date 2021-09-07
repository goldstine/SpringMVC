package org.goldstine.servlet;

import org.goldstine.dao.BookDao;
import org.goldstine.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 注解可以随便乱加，Spring底层不会取验证你的这个组件，是否如你注解所说就是一个dao层的或者就是一个servlet层的组件
 *加了注解的组件，默认的id名就是对应的首字母小写 bookServlet
 *
 * 通过注解+扫描的方式添加组件到ioc
 * (1)首先给需要添加进容器的组件加上注解，【随便加上一个注解即可@Controller,@Service,@Repository  @Component,Spring底层不会进行区分】
 * （2）配置将该注解进行扫描 通过命名空间的context:component-scan
 * (3)依赖于对应的aop支持，所以需要引入对应的aop包
 * （4）通过注解加入到容器的组件，和使用配置加入的组件行为都是默认一样的
 *          默认的id名称是类名首字母小写    默认的配置可以通过注解属性进行配置
 *          默认是单实例的
 *
 *     但是不是所有的组件都可以通过注解的方式导入容器中，如果是自己写的组件可以通过注解
 *     但是对于别人的jar中的组件，还是需要配置文件
 *
 */
//@Controller(value = "haha") //配置实例的id值
@Controller
@Scope(value = "prototype")  //配置是否是单实例还是多实例的
public class BookServlet {

    private BookDao bd;
    /**
     *   @Autowired的原理：
     *      先按照类型去容器中找到对应的组件   bookService=ioc.getBean(BookService.class);
     *              如果找到一个，就赋值
     *              没找到，抛异常
     *              找到多个，对于BookService的子类也属于BookService类型
     *                      继续通过变量名进行匹配
     *                          如果没有匹配上，报异常NoUniqueBeanDefinitionException
     *                                  没有匹配上的原因是因为通过变量名作为id进行匹配没有找到，所以出现异常
     *                                  可以通过指定的变量名id进行匹配@Quailfier("id变量名")
     *                                      如果通过@Quailfier()的方式还是没有匹配上，报异常
     *                                      找到，直接进行装配
     *                          匹配上，直接进行装配
     *
     *            发现Autowired标注的自动装配的属性默认是一定装配上的
     *              找到就装配，找不到拉到（在@Autowire(required=false)）//但是这样设置如果存在链式调用，可能出现空指针异常
     */
    @Qualifier("bookServiceEx")
    @Autowired(required = true)
//    private BookService bookService;
    private BookService bookServiceEx2;//如果找到多个相同类型的bean实例，根据变量名进行匹配查找

    public void doGet(){
        bookServiceEx2.save();
    }

    /**
     * @Autowire可以使用在属性，方法，参数，构造器，注解上
     * 方法上如果有@Autowire的话
     *  （1）这个方法也会在bean创建的时候自动运行
     *  （2）这个方法的没有个参数都会注入值
     *          参数的自动装配方式和在属性上使用@Autowire一致
     */

    @Autowired(required = true)
    public void hahaha(BookDao bookDao,@Qualifier("bookServiceEx") BookService bookServiceEx2){
        System.out.println("标注的Autowire的这个方法。。。Spring会自动运行这个方法。。。。"+bookDao+":"+bookServiceEx2);
        bd=bookDao;
    }


    /**
     * 自动装配的注解   @Autowire(Spring)   @Resource(j2ee)  @Inject(ejb)
     * @Resource：java标准
     * 区别：@Resource扩展性更强，如果切换成另外一个容器框架，@Resource还是可以使用，@Autowire就不行了
     *
     */

}
