package com.lss.demo.Proxy.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @Desc:
 * @authonr: LeeSongsheng
 * @create: 2020/06/30
 **/
public class jdkDynamicProxyUtil {

    public static <T> T newProxyInstance(T targetObject, InvocationHandler handler) {
        ClassLoader classLoader = targetObject.getClass().getClassLoader();
        Class<?>[] interfaces = targetObject.getClass().getInterfaces();
        return (T) Proxy.newProxyInstance(classLoader, interfaces, handler);
    }

}
