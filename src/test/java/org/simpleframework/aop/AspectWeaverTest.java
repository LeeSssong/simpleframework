package org.simpleframework.aop;

import com.lss.controller.superadmin.HeadLineOperationController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.simpleframework.core.BeanContainer;
import org.simpleframework.inject.DependencyInjector;

/**
 * @Desc:
 * @authonr: LeeSongsheng
 * @create: 2020/07/03
 **/
public class AspectWeaverTest {


    @DisplayName("织入通用逻辑测试：doAop")
    @Test
    public void doAopTest () {
        BeanContainer beanContainer = BeanContainer.getInstance();
        beanContainer.loadBeans("com.lss");
        new AspectWeaver().doAop();
        new DependencyInjector().doIoc();
        HeadLineOperationController headLineOperationController = (HeadLineOperationController) beanContainer.getBean(HeadLineOperationController.class);
        headLineOperationController.addHeadLine(null, null);


    }

}
