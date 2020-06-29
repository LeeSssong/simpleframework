package org.simpleframework.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;

/**
 * @Desc:保存与类相关的通用方法
 * @authonr: LeeSongsheng
 * @create: 2020/06/25
 **/
@Slf4j
public class ClassUtil {

    public static final String FILE_PROTOCOL = "file";

    /**
    * @Description: 获取包下类实例集合
    * @Param: [packageName]
    * @return: java.util.Set<java.lang.Class<?>>
    * @Author: LeeSongs
    * @Date: 2020/6/25
    */
    public static Set<Class<?>> extractPackageClass(String packageName) {
        //1.获取类的加载器
        //目的：获取项目发布的实际路径，仅通过 com.lss 无法定位到这个文件夹所在的具体路径。
        //可以直接通过绝对路径吗？不同机器之间的路径可能不同；如果打的是 war/jar，无法找到路径
        ClassLoader classLoader = getClassLoader();
        //2.通过类加载器获取到想要的包下的路径信息
        URL url = classLoader.getResource(packageName.replace(".","/"));
        if (url == null) {
            log.warn("unable to retrieve anything from package" + packageName);
            return null;
        }
        //3.依据不同的资源类型，采用不同的方式获取资源的集合，这里只要文件类型 file
        Set<Class<?>> classSet = null;
        if (url.getProtocol().equalsIgnoreCase(FILE_PROTOCOL)) {
            classSet = new HashSet<>();
            //只剩下路径
            File packageDirectory = new File(url.getPath());
            //提出目录下所有的Class文件
            extractClassFile (classSet, packageDirectory, packageName);
        }
        return classSet;
    }
/**
* @Description: 在 Dir 下递归获取 Class 文件（包括子包里的文件），通过包名，生成对象实例放入 classSet
* @Param: [emptyClassSet, fileSource, packageName]
* @return: void
* @Author: LeeSongs
* @Date: 2020/6/25
*/
    private static void extractClassFile(Set<Class<?>> emptyClassSet, File fileSource, String packageName) {
        if (!fileSource.isDirectory())
            return;
        //如果是一个文件夹，则调用其 listFiles 方法获取文件夹下的文件或文件夹
        File[] files = fileSource.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isDirectory()) {//如果是文件夹就返回 true
                    return true;
                } else {//如果是文件就提出 .class 文件
                    //获取文件的绝对路径
                    String absoluteFilePath = file.getAbsolutePath();
                    if (absoluteFilePath.endsWith(".class")) {
                        //直接加载
                        addToClassSet (absoluteFilePath);
                    }
                }
                return false;
            }
            //根据 class 文件的绝对值路径，获取并生成 class 对象，并放入 classSet 中
            private void addToClassSet(String absoluteFilePath) {
                //1. 从 class 文件的绝对值路径里提取出包含了 package 的类名
                absoluteFilePath = absoluteFilePath.replace(File.separator, ".");
                //1.1 获取从 package 开始的子串
                String className = absoluteFilePath.substring(absoluteFilePath.indexOf(packageName));
                //1.2 去掉 .class 后缀
                className = className.substring(0, className.lastIndexOf("."));
                //2. 通过反射机制获取对应的 class 对象并加入到 ClassSet 里
                Class targetClass = loadClass(className);
                emptyClassSet.add(targetClass);
            }
        });

        //递归调用，取出原始的文件夹下的子文件夹与文件
        if (files != null) {
            for (File f : files) {
                extractClassFile(emptyClassSet, f, packageName);
            }
        }
    }


    public static Class<?> loadClass (String  className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            log.error("load class error ", e);
            throw new RuntimeException();
        }
    }

    public static ClassLoader getClassLoader() {
        //通过线程所属的上下文加载器获取程序资源信息。
        return Thread.currentThread().getContextClassLoader();
    }

    /**
    * @Description: 实例化 Class，方便起见，这里仅支持无参构造函数
    * @Param: [clazz, accessible]
    * @return: T
    * @Author: LeeSongs
    * @Date: 2020/6/27
    */
    public static <T> T newInstance(Class<?> clazz, boolean accessible){

        try{
            Constructor constructor = clazz.getDeclaredConstructor();
            //用户绝对是否通过私有的构造函数获取实例
            constructor.setAccessible(accessible);
            return (T)constructor.newInstance();
        } catch (Exception e) {
            log.error("newInstance error", e);
            throw new RuntimeException(e);
        }
    }
    /**
    * @Description: 给类实例的成员变量赋值
    * @Param: field 成员变量， target 实例， value 值， accessible 是否可调用私有的构造函数
    * @return: void
    * @Author: LeeSongs
    * @Date: 2020/6/27
    */
    public static void setField(Field field, Object target, Object value, boolean accessible) {
        field.setAccessible(accessible);
        try {
            //反射
            field.set(target, value);
        } catch (IllegalAccessException e) {
            log.error("setField error", e);
            throw new RuntimeException(e);
        }
    }
}
