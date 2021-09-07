package org.goldstine.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 减余额
     * 减去某一个用户的余额
     */

    public void updateBalance(String username,int price){
        String sql="UPDATE account SET balance=balance-? WHERE username=?";
        jdbcTemplate.update(sql,price,username);
    }

    /**
     * 获取某本图书的价格
     */
    public int getPrice(String isbn){
        String sql="SELECT price FROM book WHERE isbn=?";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class, isbn);
        return integer;
    }

    /**
     * 减库存，减去某本书的库存，为了简单起见每次减去1
     */
    public void updateStock(String isbn){
        String sql="UPDATE book_stock SET stock=stock-1 WHERE isbn=?";
        jdbcTemplate.update(sql,isbn);
    }

    /**
     * 修改图书价格
     */
    public void updatePrice(String isbn,int price){
        String sql="update book set price=? where isbn=?";
        jdbcTemplate.update(sql,price,isbn);
    }

}
