package com.lss.demo.Design.FactotyMethod;

/**
 * @Desc:
 * @authonr: LeeSongsheng
 * @create: 2020/06/28
 **/
public class demo {
    public static void main(String[] args) {
        Factory factory = new ConcreteFactory();
        factory.doSomething();
    }
}
