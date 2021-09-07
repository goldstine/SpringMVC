package org.goldstine.service;

import org.goldstine.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * IOC:
 * （1）IOC是一个容器
 * （2）容器启动的时候创建所有单实例对象
 * （3）我们可以直接从容器中获取到这个对象
 *
 * SpringIOC，容器的启动过程，启动器间都做了什么（什么时候创建所有的单实例bean）
 * ioc是如何创建这些单实例bean,并如何管理的
 * 【这个东西就跟SpringMVC/Spring
 *      (1)SpringIOC容器时如何启动初始化的，容器时如何管理所有的bean对象的
 *      （2）SpringMVC是如何初始化九大组件的，以及在运行的过程中是如何调用所有的组件的
 *
 * 】
 *beanFactory是ioc容器ClassPathCmlApplicationContext的父接口
 * refresh()方法一执行，会将ioc中的所有bean实例全部都创建
 *ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();//Spring解析xml配置文件将要创建的所有bean的配置信息保存起来
 * //观看Spring如何解析xml配置文件，大框架是如何解析xml配置文件
 *
 *initMessageSource();//支持国际化功能
 *在对应的beanFactory接口中定义了对应的getBean()用于创建对应的bean实体
 *
 * 创建好的对象最终会保存在一个map中
 * ioc容器之一：保存单实例bean的地方
 * ioc就是一个容器，单实例bean保存在一个map中
 *
 * beanFactory和ApplicationContext之间的区别：
 *      BeanFactory:bean工厂接口，负责创建bean实例，容器里面保存的所有单例bean其实是一个map
 *      Spring最底层的接口
 *      ApplicationContext是容器接口，更多的是负责容器功能的实现（可以基于beanFactory创建好的对象之上完成强大的容器）
 *      容器可以从map获取这个bean,并且aop,di.在ApplicationContext接口下的这些类里面
 *
 *      BeanFactory最底层的接口，ApplicationContext留给程序员使用的ioc容器接口，ApplicationContext是beanFactory的子接口
 *
 * DefaultSingletonBeanRegistry
 *      singletonObjects：创建的对象最终会保存在singletonObjects map集合中
 *
 */


/**
 * 事务细节：
 * isolation-Ioslation:事务的隔离级别
 * propagation-Propagation:事务的传播行为
 *
 * noRollbackFor-class[]:哪些异常可以不回滚
 * noRollbackForClassName-String[](String全类名)
 *
 * rollbackFor---Class[]  哪些异常事务需要回滚
 * rollbackForClassName---String[]
 *
 * 异常：
 *      运行时异常：默认所有的运行时异常都会自动进行回滚  ，所以可以通过noRollbackFor（）属性设置默认会进行回滚的异常不进行回滚
 *      编译时异常：编译时异常都不会回滚    ；通过rollbackFor()属性将默认不会滚的异常设置为回滚异常
 *
 *
 * readOnly-boolean:设置事务为只读属性,默认为false
 *      readOnly=true加快查询速度，不用管事务那一堆操作了，可以进行事务优化
 * timeout-int:事务执行超出指定时长执行之后自动终止并回滚,单位为秒
 *
 *
 * 事务的隔离级别：
 *      数据库系统必须具有隔离并发运行各个事务的能力，使他们不会相互影响，避免各种并发问题
 *      一个事务与其他事务隔离的程度成为隔离级别，sql标准中规定了多种事务隔离级别，不同隔离级别对应不同的干扰程度
 *      隔离级别越高，数据一致性就越好，但并发行弱
 *
 * 事务的隔离级别是由于事务操作存在的问题导致的：脏读，不可重复读，幻读
 * 脏读是必须要避免的，
 *      （1）读未提交  read uncommited    会导致脏读  所以一定不能将数据库事务隔离级别设为读未提交
 *      允许事务1读取事务2未提交的修改
 *      （2）读已提交   read  commited
 *      要求事务1只能读取事务2已提交的修改
 *      （3）不可重复读  Repeatable read
 *      确保事务1可以多次从同一个字段中读取到相同的值，即事务1执行期间禁止其它事务对这个字段进行更新
 *
 *      （4）串行化
 *      确保事务1可以多次从一个表中读取到相同的行，在事务1执行期间，禁止其他事务对这个表进行添加，更新，删除操作，可以避免任何并发问题，但性能十分低下
 *
 *      隔离级别解决并发能力
 *
 *                脏读       不可重复读        幻读
 *   读未提交       会           会           会
 *    读已提交      不会          会           会
 *    可重复读      不会          不会          会
 *    串行化       不会          不会          不会
 *
 *    不同数据库支持的事务隔离级别：
 *      mysql:默认的事务隔离级别是：可重复读   mysql可以支持四种事务的隔离级别：读未提交  读已提交   可重复读   串行化
 *      【mysql在可重复读的情况下，却可以保证不会出现脏读，不可重复读  幻读】
 *      oracle：支持读已提交和串行化
 *
 *      Spring通过isolation指定对应的事事务的隔离级别
 *
 *      在xml
 *      在spring2.x事务通知中，可以在<tx:method>元素指定隔离级别</tx:method>
 *
 *
 * 关于事务的隔离级别mysql:
 *      查询事务的隔离级别：  select @@tx_isolation    mysql8对应的命令为： select @@transaction_isolation;
 *
 *      修改mysql隔离级别：
 *      set [session|global] transaction isolation level [read uncommitted|read committed | repeatable read|serializer]
 *
 * 首先将数据库的事务开启：
 * start transaction;
 * 然后读取数据select price from book where isbn='ISBN-002';
 *
 * 可重复读的情况下，只要在同一个事务期间 ，第一次是什么以后读就是什么，即使外界的数据被删除也无所谓
 *读的是保留的快照  mysql的可重复读级别就可以避免所有的问题
 *对于多个事务并发修改统一数据，数据库底层通过队列实现
 * 【对于有事务的业务逻辑，容器中保存的是这个业务对象的代理对象】
 */


/**
 * propagation-Propagation:事务的传播行为
 * 传播行为（事务的传播+事务的行为）
 *      如果有多个事务进行嵌套运行，子事务是否要和小事务共用一个事务
 *   传播行为：
 *      当一个事务方法被另外一个事务方法调用时，必须指定事务应该如何传播，例如：方法可能继续在现有事务中运行。也可能开启一个新的事务，并在自己的事务中运行，
 *      事务的传播行为可以有传播属性指定
 *      传播属性：
 *      REQUIRED(0),    如果有事务在运行，当前的方法就在这个事务内运行，否者，就启动一个新的事物，并在自己的事务中运行
 *     SUPPORTS(1),     如果有事务在运行，当前的方法就在这个事务内运行，否则他可以不运行在事务中
 *     MANDATORY(2),    当前的方法必须运行在事务中，如果没有事务在运行，抛出异常
 *     REQUIRES_NEW(3), 当前的方法必须启动一个新的事务运行，并在他自己的事务内运行，如果有事务在运行，应该将他挂起
 *     NOT_SUPPORTED(4),当前的方法不应该运行在事务中，如果有事务在运行，将他挂起
 *     NEVER(5),当前的方法不应该运行在事务中，如果有事务在运行，抛出异常
 *     NESTED(6);如果有事务在运行，当前的方法就因该在这个事务嵌套事务内运行，否则，就启动一个新的事务，并在他自己的事务内运行
 *
 *
 *     如果是REQUIRED;事务的属性是继承于大事务的
 *     而propagation = Propagation.REQUIRES_NEW可以调整
 *
 *      底层原因：
 * REQUIRED：将之前的事务用的connection传递给这个方法使用
 * REQUIRES_NEW：这个方法直接使用新的connection
 */

@Service
public class BookService {

    @Autowired
    private BookDao bookDao;

    /**
     * 结账
     */
//    @Transactional(timeout = 3,readOnly = false,rollbackFor={FileNotFoundException.class})  //实际上就是一个环绕通知AOP
//    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Transactional(propagation = Propagation.REQUIRES_NEW)//如果是REQUIRED；事务的属性都是继承于大事务的，被大事务的设置覆盖掉
    public void checkout(String username,String isbn) {
        //减库存
        bookDao.updateStock(isbn);

//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        int price = bookDao.getPrice(isbn);
        //减余额
        bookDao.updateBalance(username,price);
//        int i=10/0;

//        try {
//            new FileInputStream("D://hahahah.aa");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        //使用try-catch的方式捕获异常，对于编译时异常，即使在注解属性中设置为回滚，还是实际上不会发生回滚
//        new FileInputStream("D://hahah.aa");
    }

    @Transactional(readOnly = true,isolation = Isolation.READ_UNCOMMITTED)
    public int getPrice(String isbn){
        return bookDao.getPrice(isbn);
    }

    //修改图书价格
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updatePrice(String isbn,int price){
        bookDao.updatePrice(isbn,price);
//        int i=10/0;
    }


    //如果在同一个类中的事务调用
    @Transactional
    public void mulTx(){
        checkout("Tom","ISBN-001");
        updatePrice("ISBN-002",998);
        int i=10/0;
    }
    /**
     * 如果是本类方法调用事务方法，相当于是同一个事务
     *
     * 由于事务控制的底层是通过代理对象实现的，所以如果在不同而类中调用事务方法，相当于代理对象
     *
     * MulServiceProxy.mulTx(){
     *     bookServiceProxy.checkout();
     *     bookServiceProxy.updatePrice();
     * }
     *
     * 本类方法的嵌套调用就只是一个事务：
     * BookServiceproxy.mulTx(){
     *     checkout();
     *     updatePrice();
     *     //相当于,直接组成同一个事务
     *     bookDao.updateStock(isbn);
     *     int price=bookDao.getPrice(isbn);
     *     bookDao.updateBlance(username,price);
     *     bookDao.updatePrice(isbn,price);
     * }
     *
     */

}
