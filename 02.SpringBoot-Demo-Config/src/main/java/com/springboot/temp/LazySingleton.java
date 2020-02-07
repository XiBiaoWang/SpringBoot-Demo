package com.springboot.temp;

/**
 * @Classname LazySingleton
 * @Description 单例模式 懒汉模式
 */
public class LazySingleton {
    private volatile static LazySingleton instance;
    private LazySingleton(){}
    public static LazySingleton getInstance(){
        if(instance == null){
            synchronized(LazySingleton.class) {
                if(instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args){
        new Thread(()->{
            LazySingleton singleton = LazySingleton.getInstance();
            System.out.println(singleton);
        }).start();

        new Thread(()->{
            LazySingleton singleton = LazySingleton.getInstance();
            System.out.println(singleton);
        }).start();
    }
}
