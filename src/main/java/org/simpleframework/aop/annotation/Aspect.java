package org.simpleframework.aop.annotation;

import java.lang.annotation.*;

/**
 * @Desc:标记横切逻辑，告诉横切逻辑该织入那些类
 * @authonr: LeeSongsheng
 * @create: 2020/07/02
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    //当 value 为 Controller 时，会将横切逻辑织入到所有被 @Controller 标记的类里
    Class<? extends Annotation> value();

    //写入 AspectJ 表达式
//    String pointcut();
}
