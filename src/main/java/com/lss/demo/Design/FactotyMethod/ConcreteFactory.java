package com.lss.demo.Design.FactotyMethod;

/**
 * @Desc:
 * @authonr: LeeSongsheng
 * @create: 2020/06/28
 **/
public class ConcreteFactory extends Factory{
    @Override
    public Product factoryMethod() {
        return new ConcreteProduct();
    }
}
