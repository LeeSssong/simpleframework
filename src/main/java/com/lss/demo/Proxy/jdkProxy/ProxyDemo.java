package com.lss.demo.Proxy.jdkProxy;

import java.lang.reflect.InvocationHandler;

/**
 * @Desc:
 * @authonr: LeeSongsheng
 * @create: 2020/06/30
 **/
public class ProxyDemo {
    public static void main(String[] args) {
        ToCPayment toCPayment = new ToCPaymentImpl();
        InvocationHandler handler = new AlipayInvocationHandler(toCPayment);
        ToCPayment toCProxy = jdkDynamicProxyUtil.newProxyInstance(toCPayment, handler);
        toCProxy.pay();
    }


}
