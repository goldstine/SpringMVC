package org.goldstine.factory;

import org.goldstine.bean.Book;
import org.springframework.beans.factory.FactoryBean;

/**
 * 通过实现FactoryBean接口的方式创建一个工厂
 *Spring会自动调用工厂方法创建实例
 *
 * （1）编写一个FactoryBean的实现类
 * （2）在spring配置文件中进行注册
 *
 */
public class MyFactoryBeanImpl implements FactoryBean<Book> {

    /**
     * 工厂方法：getObject()
     * 返回创建的对象
     *
     * 在容器启动之前不会创建该实例bean对象，不管是单实例还是多实例多不会再容器启动之前创建实例
     * 在获取对应的实例对象时进行创建对应的bean对象
     */
    @Override
    public Book getObject() throws Exception {
        System.out.println("SpringIOC 帮你创建对象 ，实现类FactoryBean接口的类");
        Book book = new Book();
        book.setAuthor("曹雪芹");
        book.setBookName("红楼梦");
        return book;
    }

    /**
     * 返回创建的对象类型
     * Spring会自动调用这个方法来确认创建的对象是什么类型
     * @return
     */
    @Override
    public Class<?> getObjectType() {
        return Book.class;
    }

    /**
     * 是否创建单例对象
     * @return
     */
    @Override
    public boolean isSingleton() {
        return true;
    }
}
