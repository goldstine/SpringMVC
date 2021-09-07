package org.goldstine.factory;

import org.goldstine.bean.AirPlane;

/**
 * 创建一个实例工厂，需要通过先创建工厂对象，通过工厂对象创建bean对象
 */
public class InstanceFactory {
    public AirPlane getAirPlane(String jzName){

        System.out.println("通过实例工厂创建对象............");

        AirPlane airPlane = new AirPlane();
        airPlane.setFdj("太行");
        airPlane.setFdjName("goldstine");
        airPlane.setJzName(jzName);
        airPlane.setPersonNum(300);
        airPlane.setYc("198.89m");
        return airPlane;

    }
}
