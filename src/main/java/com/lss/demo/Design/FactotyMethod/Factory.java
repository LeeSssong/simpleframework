package com.lss.demo.Design.FactotyMethod;

/**
 * @Desc:
 * @authonr: LeeSongsheng
 * @create: 2020/06/28
 **/
public abstract class Factory {
    abstract public Product factoryMethod();
    public void doSomething() {
        Product product = factoryMethod();
        // do something with the product
    }
}
