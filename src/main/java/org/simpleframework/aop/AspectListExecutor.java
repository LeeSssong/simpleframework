package org.simpleframework.aop;

import lombok.Getter;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.simpleframework.aop.aspect.AspectInfo;
import org.simpleframework.util.ValidationUtil;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Desc:横切逻辑执行
 * @authonr: LeeSongsheng
 * @create: 2020/07/02
 **/
public class AspectListExecutor implements MethodInterceptor {

    private Class<?> targetClass;

    @Getter
    private List<AspectInfo> sortedAspectInfoList;

    public AspectListExecutor(Class<?> targetClass, List<AspectInfo> aspectInfoList) {
        this.targetClass = targetClass;
        this.sortedAspectInfoList = sortAspectInfoList(aspectInfoList);
    }

    /**
    * @Description: 按照 order 的值进行升序排序，确保 order 值小的 aspect 先被织入
    * @Param: [aspectInfoList]
    * @return: java.util.List<org.simpleframework.aop.aspect.AspectInfo>
    * @Author: LeeSongs
    * @Date: 2020/7/2
    */
    private List<AspectInfo> sortAspectInfoList(List<AspectInfo> aspectInfoList) {
        Collections.sort(aspectInfoList, new Comparator<AspectInfo>() {

            //0：不排序，正数：升序，负数：降序
            @Override
            public int compare(AspectInfo o1, AspectInfo o2) {
                return o1.getOrderIndex() - o2.getOrderIndex();
            }
        });

        return aspectInfoList;
    }


    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        Object returnValue = null;
        if (ValidationUtil.isEmpty(sortedAspectInfoList))
            return null;
        //1.按照 order 的顺序升序执行完所有 Aspect 的 before 方法
        invokeBeforeAdvices(method, args);

        try {
            //2.执行被代理类的方法
            returnValue = methodProxy.invokeSuper(proxy, args);
            //3.如果被代理方法正常返回，则按照 order 的顺序降序执行完所有 Aspect 的 afterReturning 方法
            returnValue =  invokeAfterReturningAdvices(method, args, returnValue);
        } catch (Exception e) {
            //4.如果被代理方法抛出异常，则按照 order 的顺序降序执行完所有 Aspect 的 afterThrowing 方法
            invokeAfterThrowingAdvices(method, args, e);
        }


        return returnValue;
    }

    private void invokeAfterThrowingAdvices(Method method, Object[] args, Exception e) throws Throwable {
        for (int i = sortedAspectInfoList.size() - 1; i >= 0; i--)
            sortedAspectInfoList.get(i).getAspectObject().afterThrowing(targetClass, method, args, e);
    }

    private Object invokeAfterReturningAdvices(Method method, Object[] args, Object returnValue) throws Throwable {
        Object result = null;
        for (int i = sortedAspectInfoList.size() - 1; i >= 0; i--)
            result = sortedAspectInfoList.get(i).getAspectObject().afterReturning(targetClass, method, args, returnValue);
        return result;
    }

    private void invokeBeforeAdvices(Method method, Object[] args) throws Throwable {
        for (AspectInfo aspectInfo : sortedAspectInfoList) {
            aspectInfo.getAspectObject().before(targetClass, method, args);
        }
    }
}
