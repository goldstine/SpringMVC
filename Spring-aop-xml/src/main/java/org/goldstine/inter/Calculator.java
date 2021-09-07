package org.goldstine.inter;

/**
 * 接口不加在容器中，实际上是可以加的，加了也不会创建对象，只要一个这个组件是一个接口
 * 相当于告诉Spring,IOC容器中有这种类型的组件
 *
 */
public interface Calculator {
    /**
     * 定义四则运算的方法
     */
    public int add(int i,int j);
    public int sub(int i,int j);
    public int mul(int i,int j);
    public int div(int i,int j);
}
