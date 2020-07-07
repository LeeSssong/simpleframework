package org.simpleframework.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * @Desc:
 * @authonr: LeeSongsheng
 * @create: 2020/07/02
 **/
public class ProxyCreator {

    /**
    * @Description: 创建动态代理对象并返回
    * @Param: [targetClass, methodInterceptor]
    * @return: java.lang.Object
    * @Author: LeeSongs
    * @Date: 2020/7/2
    */
    public static Object createProxy (Class<?> targetClass, MethodInterceptor methodInterceptor) {
        return Enhancer.create(targetClass, methodInterceptor);
    }
}
