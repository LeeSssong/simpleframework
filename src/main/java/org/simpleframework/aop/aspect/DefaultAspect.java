package org.simpleframework.aop.aspect;

import java.lang.reflect.Method;

/**
 * @Desc:
 * @authonr: LeeSongsheng
 * @create: 2020/07/02
 **/
public abstract class DefaultAspect {

    /**
    * @Description: 钩子方法，让具体类去实现
    * @Param: targetClass: 被代理的类；method：被代理的目标方法；args：被代理的目标方法对应的参数列表
    * @return: void
    * @Author: LeeSongs
    * @Date: 2020/7/2
    */
    public void before (Class<?> targetClass, Method method, Object[] args) throws Throwable{}


    /**
    * @Description:
    * @Param: targetClass: 被代理的类；method：被代理的目标方法；args：被代理的目标方法对应的参数列表；returnValue：被代理的目标方法执行后的返回值
    * @return: java.lang.Object
    * @Author: LeeSongs
    * @Date: 2020/7/2
    */
    public Object afterReturning (Class<?> targetClass, Method method, Object[] args, Object returnValue) throws Throwable{
        return returnValue;
    }


    public void afterThrowing (Class<?> target, Method method, Object[] args, Throwable e) throws Throwable {}
}
