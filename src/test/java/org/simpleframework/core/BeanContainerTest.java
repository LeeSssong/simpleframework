package org.simpleframework.core;

import com.lss.controller.DispatcherServlet;
import com.lss.controller.frontend.MainPageController;
import com.lss.service.solo.HeadLineService;
import com.lss.service.solo.impl.HeadLineServiceImpl;
import org.junit.jupiter.api.*;
import org.simpleframework.core.annotation.Controller;

/**
 * @Desc:
 * @authonr: LeeSongsheng
 * @create: 2020/06/27
 **/
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BeanContainerTest {
    private static BeanContainer beanContainer;
    @BeforeAll
    static void init() {
        beanContainer = BeanContainer.getInstance();
    }


    @DisplayName("加载目标类及其实例到 BeanContainer : loadBeansTest")
    @Order(1)
    @Test
    public void loadBeansTest(){
        Assertions.assertEquals(false, beanContainer.isLoaded());
        beanContainer.loadBeans("com.lss");
        Assertions.assertEquals(6, beanContainer.size());
        Assertions.assertEquals(true, beanContainer.isLoaded());
    }

    @DisplayName("根据类获取其实例 : getBeanTest")
    @Order(2)
    @Test
    public void getBeansTest() {
        MainPageController controller = (MainPageController) beanContainer.getBean(MainPageController.class);
        Assertions.assertEquals(true, controller instanceof MainPageController);

        DispatcherServlet dispatcherServlet = (DispatcherServlet) beanContainer.getBean(DispatcherServlet.class);
        Assertions.assertEquals(null, dispatcherServlet);
    }

    @DisplayName("根据注解获取对应的实例 : getClassesByAnnotation")
    @Test
    @Order(3)
    public void getClassesByAnnotationTest() {
        Assertions.assertEquals(true, beanContainer.isLoaded());
        Assertions.assertEquals(3, beanContainer.getClassesByAnnotation(Controller.class).size());
    }

    @DisplayName("根据接口获取实现类 : getClassesBySupperTest")
    @Order(4)
    @Test
    public void getClassesBySupperTest() {
        Assertions.assertEquals(true, beanContainer.isLoaded());
        Assertions.assertEquals(true, beanContainer.getClassesBySupper(HeadLineService.class).contains(HeadLineServiceImpl.class));
    }
}
