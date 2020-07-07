package org.simpleframework.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Desc:具有相同横切逻辑的 aspect 可能不止一个，即 Aspect 中的value相同但横切逻辑不同的实现有多个，用户可以使用此标记决定使用哪个
 * @authonr: LeeSongsheng
 * @create: 2020/07/02
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Order {
    int value();//值越小，优先级越高
}
