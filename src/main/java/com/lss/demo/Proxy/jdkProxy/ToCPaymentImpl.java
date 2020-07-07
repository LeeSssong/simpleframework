package com.lss.demo.Proxy.jdkProxy;

/**
 * @Desc:
 * @authonr: LeeSongsheng
 * @create: 2020/06/30
 **/
public class ToCPaymentImpl implements ToCPayment{

    @Override
    public void pay() {
        System.out.println("ToCPaymentImpl");
    }
}
