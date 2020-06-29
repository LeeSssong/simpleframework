package org.simpleframework.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.annotation.Component;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.core.annotation.Repository;
import org.simpleframework.core.annotation.Service;
import org.simpleframework.util.ClassUtil;
import org.simpleframework.util.ValidationUtil;

import java.awt.*;
import java.lang.annotation.Annotation;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Desc:1.保存 Class 对象及其实例的载体。2.加载被注解标记的类作为 bean。3.容器的一系列操作方式。
 * @authonr: LeeSongsheng
 * @create: 2020/06/27
 **/
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanContainer {

    //容器是否加载的标志。
    private boolean loaded = false;
    /**
     * @Description: 获取 Bean 容器实例
     * @Param: []
     * @return: org.simpleframework.core.BeanContainer
     * @Author: LeeSongs
     * @Date: 2020/6/27
     */
    public static BeanContainer getInstance() {
        return ContainerHolder.HOLDER.instance;
    }

    private enum ContainerHolder {
        HOLDER;
        private BeanContainer instance;
        ContainerHolder(){
            instance = new BeanContainer();
        }
    }

    //存放所有被配置标记的目标对象的Map
    private final Map<Class<?>, Object> beanMap = new ConcurrentHashMap<>();

    //实现容器的加载
    //1.获取配置（管理注解）
    private static final List<Class< ? extends Annotation>> BEAN_ANNOTATION
            = Arrays.asList(Component.class, Controller.class, Repository.class, Service.class);


    /**
    * @Description: 3.依据配置（扫描所有的 bean）提取 Class 对象，连同实例一并存入容器
    * @Param: [packageName]
    * @return: void
    * @Author: LeeSongs
    * @Date: 2020/6/27
    */
    public synchronized void loadBeans(String packageName) {
        //0.防止同时多个线程先后进入，实例化多个容器
        //1.判断容器是否被加载过
        if (isLoaded()) {
            log.warn("BeanContainer has been loader");
            return;
        }
        //2.获取指定范围内的 Class 对象
        Set<Class<?>> classSet = ClassUtil.extractPackageClass(packageName);
        if (ValidationUtil.isEmpty(classSet)) {
            log.warn("extract nothing from packageName" + packageName);
            return;
        }

        //3.扫描获取到的类是否被注解标记
        for (Class<?> clazz : classSet) {
            for (Class<? extends Annotation> annotation : BEAN_ANNOTATION) {
                //如果类上标记了定义的注解
                if (clazz.isAnnotationPresent(annotation)) {
                    //类作为键，目标类的实例作为值，放入到 beanMap 中。
                    beanMap.put(clazz,ClassUtil.newInstance(clazz, true));
                }
            }
        }
        loaded = true;
    }

    /**
    * @Description: 判断容器是否被加载过
    * @Param: []
    * @return: boolean
    * @Author: LeeSongs
    * @Date: 2020/6/27
    */
    public boolean isLoaded() {
        return loaded;
    }

    public int size() {
        return beanMap.size();
    }

    /**
    * @Description: 将 Class 作为键，对应的实例作为值传入 Bean 容器，返回实例。
    * @Param: [clazz, bean]
    * @return: java.lang.Object
    * @Author: LeeSongs
    * @Date: 2020/6/27
    */
    public Object addBean (Class<?> clazz, Object bean) {
        return beanMap.put(clazz, bean);

    }

    /**
    * @Description: 移出一个 IOC 容器管理的对象
    * @Param: [clazz]
    * @return: java.lang.Object
    * @Author: LeeSongs
    * @Date: 2020/6/27
    */
    public Object removeBean (Class<?> clazz) {
        return beanMap.remove(clazz);
    }

    /**
    * @Description: 根据 class 对象返回对应的实例
    * @Param: [clazz]
    * @return: java.lang.Object
    * @Author: LeeSongs
    * @Date: 2020/6/27
    */
    public Object getBean (Class<?> clazz) {
        return beanMap.get(clazz);
    }

    /**
    * @Description: 获取容器管理的所有 Class 对象集合
    * @Param: []
    * @return: java.util.Set<java.lang.Class<?>>
    * @Author: LeeSongs
    * @Date: 2020/6/27
    */
    public Set<Class<?>> getClasses() {
        return beanMap.keySet();
    }

    /**
    * @Description: 获取所有的 Bean 集合
    * @Param: []
    * @return: java.util.Set<java.lang.Object>
    * @Author: LeeSongs
    * @Date: 2020/6/27
    */
    public Set<Object> getBeans() {
        return new HashSet<>(beanMap.values());
    }

    /**
    * @Description: 根据注解筛选出 Bean 的 Class 集合
    * @Param: [annotation]
    * @return: java.util.Set<java.lang.Class<?>>
    * @Author: LeeSongs
    * @Date: 2020/6/27
    */
    public Set<Class<?>> getClassesByAnnotation(Class<? extends Annotation> annotation) {
        //1.获取 beanMap 的所有 class 对象
        Set<Class<?>> keySet = getClasses();
        if(ValidationUtil.isEmpty(keySet)) {
            log.warn("nothing in beanMap");
            return null;
        }
        //2.通过注解筛选被注解标记的 class 对象，并添加到 classSet 里
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> clazz : keySet) {
            //类是否有相关注解标记
            if (clazz.isAnnotationPresent(annotation)) {
                classSet.add(clazz);
            }
        }
        return classSet.size() > 0 ? classSet : null;
    }

    /**
    * @Description: 通过接口或者父类获取实现类或者子类的 class 集合，不包括其本身
    * @Param: [interfaceOfClass]
    * @return: java.util.Set<java.lang.Class<?>>
    * @Author: LeeSongs
    * @Date: 2020/6/27
    */
    public Set<Class<?>> getClassesBySupper(Class<?> interfaceOfClass) {
        //1.获取 beanMap 的所有 class 对象
        Set<Class<?>> keySet = getClasses();
        if(ValidationUtil.isEmpty(keySet)) {
            log.warn("nothing in beanMap");
            return null;
        }
        //2.判断 keySet 里的元素是否是传入呃接口或类的子类，如果是，则添加到 classSet 里
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> clazz : keySet) {
            //判断 keySet 里的元素是否是传入呃接口或类的子类
            if (interfaceOfClass.isAssignableFrom(clazz) && !clazz.equals(interfaceOfClass)) {
                classSet.add(clazz);
            }
        }
        return classSet.size() > 0 ? classSet : null;
    }
}
