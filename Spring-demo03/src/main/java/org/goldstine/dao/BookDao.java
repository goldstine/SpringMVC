package org.goldstine.dao;

import org.goldstine.bean.Book;
import org.springframework.stereotype.Repository;

@Repository
public class BookDao extends BaseDao<Book>{

    @Override
    public void save() {
        System.out.println("保存book对象.....");
    }
}
