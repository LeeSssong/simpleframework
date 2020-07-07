package org.simpleframework.aop.aspect;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @Desc：存放每个 aspect 横切逻辑的信息
 * @authonr: LeeSongsheng
 * @create: 2020/07/02
 **/
@AllArgsConstructor
@Setter
@Getter
public class AspectInfo {
    private int orderIndex;//执行顺序
    private DefaultAspect aspectObject;
}
