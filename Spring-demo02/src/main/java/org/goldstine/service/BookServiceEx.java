package org.goldstine.service;

import org.goldstine.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BookServiceEx extends BookService{

//    @Autowired
    @Resource
    private BookDao bookDao;

    @Override
    public void save() {

        System.out.println("BookServiceEx调用dao....");
        bookDao.saveBook();
    }
}
