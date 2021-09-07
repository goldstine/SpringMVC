package org.goldstine.factory;

import org.goldstine.bean.AirPlane;

/**
 * 创建静态工厂，直接通过类名调用静态方法获得对象
 */
public class StaticFactory {
    //创建静态方法
    public static AirPlane getAirPlane(String jzName){
        System.out.println("通过静态工厂创建对象......");
        AirPlane airPlane = new AirPlane();
        airPlane.setFdj("太行");
        airPlane.setFdjName("goldstine");
        airPlane.setJzName(jzName);
        airPlane.setPersonNum(300);
        airPlane.setYc("198.89m");
        return airPlane;

    }
}
