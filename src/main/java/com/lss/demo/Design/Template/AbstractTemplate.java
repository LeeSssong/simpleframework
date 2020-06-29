package com.lss.demo.Design.Template;

/**
 * @Desc:模板方法设计模式
 * @authonr: LeeSongsheng
 * @create: 2020/06/28
 **/
public abstract class AbstractTemplate {
    //模板方法
    public void templateMethod () {
        concreteMethod();
        hookMethod();
        abstractMethod();
    }

    //具体方法，不需要子类实现
    public final void concreteMethod () {
        System.out.println("模板自带的实现方法，不需改变");
    }

    //钩子方法，子类可以依据实际情况实现
    protected void hookMethod () {}

    //抽象方法，必须让子类实现的方法
    public abstract void abstractMethod();
}
