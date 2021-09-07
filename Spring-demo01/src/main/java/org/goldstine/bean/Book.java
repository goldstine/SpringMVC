package org.goldstine.bean;

public class Book {
    private String bookName;
    private String author;

    public Book(String bookName) {
        this.bookName = bookName;
    }

    //定义初始化方法
    public void myInit(){
        System.out.println("这是图书的初始化方法.....");
    }
    //定义销毁方法
    public void myDestory(){
        System.out.println("z这是图书的销毁方法.....");
    }

    public Book() {
        System.out.println("book对象被创建了....");
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
