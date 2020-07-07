package org.simpleframework.aop;

import org.simpleframework.aop.annotation.Aspect;
import org.simpleframework.aop.annotation.Order;
import org.simpleframework.aop.aspect.AspectInfo;
import org.simpleframework.aop.aspect.DefaultAspect;
import org.simpleframework.core.BeanContainer;
import org.simpleframework.util.ValidationUtil;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * @Desc:
 * @authonr: LeeSongsheng
 * @create: 2020/07/02
 **/
public class AspectWeaver {
    private BeanContainer beanContainer;
    public AspectWeaver(){
        this.beanContainer = BeanContainer.getInstance();
    }

    public void doAop () {
        //1.获取所有的切面类
        Set<Class<?>> aspectSet = beanContainer.getClassesByAnnotation(Aspect.class);
        //2.将不同切面类（value 不同）按照不同的织入目标进行切分
        Map<Class<? extends Annotation>, List<AspectInfo>> categorizedMap = new HashMap<>();
        if (ValidationUtil.isEmpty(aspectSet))
            return;
        for (Class<?> aspectClass : aspectSet) {
            if (verifyAspect(aspectClass)) {
                categorizeAspect(categorizedMap, aspectClass);
            } else {
                throw new RuntimeException("@Aspect and @Order have not been added to the Aspect class," +
                        " or Aspect class does not extend from DefaultAspect, or the value in Aspect Tag equals @Aspect");
            }
        }
        //3.按照不同的织入目标分别去按序织入 Aspect 的逻辑
        if (ValidationUtil.isEmpty(categorizedMap))
            return;
        for (Class<? extends Annotation> category : categorizedMap.keySet()) {
            weaveByCategory (category, categorizedMap.get(category));
        }

        
        
        
    }


    private void weaveByCategory(Class<? extends Annotation> category, List<AspectInfo> aspectInfoList) {
        //1.通过注解，获取被相同注解标记的被代理类的集合
        Set<Class<?>> classSet = beanContainer.getClassesByAnnotation(category);
        if (ValidationUtil.isEmpty(classSet))
            return;
        //2.遍历被代理类，分别为每个代理类生成动态代理实例
        for (Class<?> targetClass : classSet) {
            AspectListExecutor aspectListExecutor = new AspectListExecutor(targetClass, aspectInfoList);
            Object proxyBean = ProxyCreator.createProxy(targetClass, aspectListExecutor);
            //3.将动态代理对象实例添加到容器里，取代未被代理前的类实例
            beanContainer.addBean(targetClass, proxyBean);
        }

    }

    private void categorizeAspect(Map<Class<? extends Annotation>, List<AspectInfo>> categorizedMap, Class<?> aspectClass) {
        Order orderTag = aspectClass.getAnnotation(Order.class);
        Aspect aspectTag = aspectClass.getAnnotation(Aspect.class);
        DefaultAspect aspect = (DefaultAspect) beanContainer.getBean(aspectClass);
        AspectInfo aspectInfo = new AspectInfo(orderTag.value(), aspect);
        if (!categorizedMap.containsKey(aspectTag.value())) {
            //如果织入的 joinpoint 第一次出现，则以该 joinpoint 为 key ，以新创建的 List<AspectInfo> 为 value
            List<AspectInfo> aspectInfoList = new ArrayList<>();
            aspectInfoList.add(aspectInfo);
            categorizedMap.put(aspectTag.value(), aspectInfoList);
        } else {
            //如果织入的 joinpoint 不是第一次出现，则往 joinpoint 对应的 value 里添加新的 Aspect 逻辑
            List<AspectInfo> aspectInfoList = categorizedMap.get(aspectTag.value());
            aspectInfoList.add(aspectInfo);
        }
    }

    /**
    * @Description: 框架中一定要遵守给 Aspect 类添加 @Aspect 和 @Order 标签的规范，同时，必须继承自 DefaultAspect.class，@Aspect属性值不能是它本身
    * @Param: [aspectClass]
    * @return: boolean
    * @Author: LeeSongs
    * @Date: 2020/7/3
    */
    private boolean verifyAspect(Class<?> aspectClass) {
        return aspectClass.isAnnotationPresent(Aspect.class) &&
                aspectClass.isAnnotationPresent(Order.class) &&
                DefaultAspect.class.isAssignableFrom(aspectClass) &&
                aspectClass.getAnnotation(Aspect.class).value() != Aspect.class;
    }
}
