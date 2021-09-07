package org.goldstine.dao;

import org.goldstine.bean.Lock;

public interface LockDao {
    //根据id查找锁
    //查锁的时候将所有的钥匙都查出来
    public Lock getLockById(Integer id);
    public Lock getLockByIdSimple(Integer id);
}
