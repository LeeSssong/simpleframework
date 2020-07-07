package org.simpleframework.aop.aspect;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.simpleframework.aop.AspectListExecutor;
import org.simpleframework.aop.aspect.mock.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc:
 * @authonr: LeeSongsheng
 * @create: 2020/07/02
 **/
public class AspectListExecutorTest {

    @DisplayName("Aspect 排序：sortAspectList")
    @Test
    public void sortTest () {
        List<AspectInfo> aspectInfoList = new ArrayList<>();
        aspectInfoList.add(new AspectInfo(3, new mock1()));
        aspectInfoList.add(new AspectInfo(5, new mock2()));
        aspectInfoList.add(new AspectInfo(2, new mock3()));
        aspectInfoList.add(new AspectInfo(4, new mock4()));
        aspectInfoList.add(new AspectInfo(1, new mock5()));
        AspectListExecutor aspectListExecutor = new AspectListExecutor(AspectListExecutorTest.class, aspectInfoList);
        List<AspectInfo> sortedAspectInfoList = aspectListExecutor.getSortedAspectInfoList();
        for (AspectInfo aspectInfo : sortedAspectInfoList) {
            System.out.println(aspectInfo.getAspectObject().getClass().getName());
        }

    }
}
