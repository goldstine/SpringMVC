package org.goldstine.bean;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 如果使用注解出现错误，不能使用，可能是编译器jdk不支持  jdk1.4不支持注解
 */
public class Person {

    private Double salary;

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getSalary() {
        return salary;
    }

    public Person(Car car, List<Book> books) {
        this.car = car;
        this.books = books;
    }

    public Person(Car car) {
        this.car = car;
        System.out.println("可以为car赋值的有参构造器....");
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    private String lastName="小王";
    private Integer age;
    private String sex;
    private String email;

    //除了对基本数据类型的赋值
    //对于引用数据类型的赋值
    private Car car;

    //对于集合数据类型
    private List<Book> books;
    private Map<String,Object> maps;
    private Properties properties;

    public Person(Double salary, String lastName, Integer age, String sex, String email, Car car, List<Book> books, Map<String, Object> maps, Properties properties) {
        this.salary = salary;
        this.lastName = lastName;
        this.age = age;
        this.sex = sex;
        this.email = email;
        this.car = car;
        this.books = books;
        this.maps = maps;
        this.properties = properties;
    }

    //    //对于重载三个参数的构造器
//    public Person(String lastName, Integer age, String sex) {
//        this.lastName = lastName;
//        this.age = age;
//        this.sex = sex;
//        System.out.println("1111通过有参构造器进行赋值.....");
//    }
//    public Person(String lastName, String email, String sex) {
//        this.lastName = lastName;
//        this.sex = sex;
//        this.email = email;
//        System.out.println("2222通过有参构造器进行赋值.....");
//    }
//
//    public Person(String lastName, Integer age, String sex, String email) {
//        this.lastName = lastName;
//        this.age = age;
//        this.sex = sex;
//        this.email = email;
//        System.out.println("通过有参构造器进行赋值.....");
//    }

    public Person() {
        System.out.println("Person对象创建了....");
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        System.out.println("setLastName方法被调用...."+lastName);
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "salary=" + salary +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", car=" + car +
                ", books=" + books +
                ", maps=" + maps +
                ", properties=" + properties +
                '}';
    }
}
