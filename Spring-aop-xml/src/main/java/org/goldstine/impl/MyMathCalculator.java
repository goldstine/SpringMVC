package org.goldstine.impl;

import org.springframework.stereotype.Service;

public class MyMathCalculator /*implements Calculator*/ {

//    对于没有实现接口的类也可以创建代理对象

    public int add(int i,double j){
        return 0;
    }

//    @Override
    public int add(int i, int j) {

        int result=i+j;
        System.out.println("方法内部执行。。。。。");
        return result;
    }

//    @Override
    public int sub(int i, int j) {

        int result=i-j;
        System.out.println("方法内部执行。。。。。");
        return result;
    }

//    @Override
    public int mul(int i, int j) {

        int result=i*j;
        System.out.println("方法内部执行。。。。。");
        return result;
    }

//    @Override
    public int div(int i, int j) {

        int result=i/j;
        System.out.println("方法内部执行。。。。。");
        return result;
    }
}
