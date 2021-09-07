package org.goldstine.service;

import org.goldstine.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BookService {

//    @Autowired
    @Resource
    private BookDao bookDao;

    public void save(){
        System.out.println("bookService。。。正在调用BookDao");
        bookDao.saveBook();
    }
}
