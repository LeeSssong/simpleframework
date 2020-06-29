package org.simpleframework.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

/**
 * @Desc:
 * @authonr: LeeSongsheng
 * @create: 2020/06/26
 **/
public class ClassUtilTest {

    @DisplayName(value = "提取目标类方法：extractPackageClassTest")
    @Test
    public void extractPackageClassTest () {
        Set<Class<?>> classSet = ClassUtil.extractPackageClass("com.lss.entity");
        System.out.println(classSet);
        Assertions.assertEquals(4, classSet.size());
    }
}
