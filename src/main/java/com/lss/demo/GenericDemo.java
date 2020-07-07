package com.lss.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc:
 * @authonr: LeeSongsheng
 * @create: 2020/07/05
 **/
public class GenericDemo {
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        List list = integerList;    //可以赋值List<Integer>
        list.add("String");        //添加String类型的数据
        System.out.println(list);  //输出结果：[1, 2, String]

        //List<Object>  objectList = integerList;    //编译报错
        //Object[] objectArr = new Integer[]{};

        List<Object> objectList = new ArrayList<>();
        objectList.add(1);
        objectList.add("this");
        System.out.println(objectList);
        
    }
}
