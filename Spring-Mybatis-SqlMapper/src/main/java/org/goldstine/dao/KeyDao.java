package org.goldstine.dao;

import org.goldstine.bean.Key;

public interface KeyDao {

    //将钥匙的所有信息查出来
    //包括锁的信息，所以是关联查询
    public Key getKeyById(Integer id);
    public Key getKeyByIdSimple(Integer id);
}
