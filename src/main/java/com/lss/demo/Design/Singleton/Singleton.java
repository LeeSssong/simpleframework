package com.lss.demo.Design.Singleton;

/**
 * @Desc:
 * @authonr: LeeSongsheng
 * @create: 2020/06/26
 **/
public class Singleton {
    private Singleton() {
    }

    public static Singleton getInstance() {
        return ContainerHolder.HOLDER.instance;
    }

    private enum ContainerHolder {
        HOLDER;
        private Singleton instance;

        ContainerHolder() {
            instance = new Singleton();
        }
    }

}
