package com.springboot.temp;

/**
 * @Classname HungrySingleton
 * @Description 单例模式：饿汉模式
 */
public class HungrySingleton {
    private static HungrySingleton instance = new HungrySingleton();
    private HungrySingleton(){}

    public static HungrySingleton getInstance(){
        return instance;
    }

    public static void main(String[] args){
        HungrySingleton singleton = HungrySingleton.getInstance();
    }
}
