package org.goldstine.dao;

import org.goldstine.bean.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends BaseDao<User>{

    @Override
    public void save() {
        System.out.println("保存对应的user对象....");
    }
}
