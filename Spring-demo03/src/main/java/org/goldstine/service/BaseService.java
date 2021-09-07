package org.goldstine.service;

import org.goldstine.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 泛型依赖注入，诸如一个组件的时候，它的泛型也是参考标准
 * @param <T>
 */
public class BaseService<T> {

    @Autowired
    private BaseDao<T> baseDao;

    public void save(){
        System.out.println("自动注入dao:"+baseDao);
        baseDao.save();
    }
}
