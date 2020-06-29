package com.lss.demo.Design.Singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Desc:
 * @authonr: LeeSongsheng
 * @create: 2020/06/26
 **/
public class SingletonDemo {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //正常获取
        Singleton singleton1 = Singleton.getInstance();

        //反射获取
        Class clazz = Singleton.class;
        Constructor con = clazz.getDeclaredConstructor();
        con.setAccessible(true);
        Singleton singleton2 = (Singleton) con.newInstance();


        System.out.println(singleton1);
        System.out.println(singleton2.getInstance());
        System.out.println(singleton1 == singleton2.getInstance());
    }
}
