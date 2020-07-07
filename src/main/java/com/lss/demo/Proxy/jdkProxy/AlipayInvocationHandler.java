package com.lss.demo.Proxy.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Desc:
 * @authonr: LeeSongsheng
 * @create: 2020/06/30
 **/
public class AlipayInvocationHandler implements InvocationHandler {

    //需要被代理的类
    private Object targetObject;

    public AlipayInvocationHandler(Object targetObject) {
        this.targetObject = targetObject;
    }

    /**
    * @Description: 作为一个切面，封装通用的横切面逻辑
    * @Param: [proxy, method, args]
    * @return: java.lang.Object
    * @Author: LeeSongs
    * @Date: 2020/6/30
    */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        beforePay();
        Object result = method.invoke(targetObject, args);
        afterPay();
        return result;
    }

    private void beforePay(){
        System.out.println("beforePay");
    }
    private void afterPay(){
        System.out.println("afterPay");
    }
}
