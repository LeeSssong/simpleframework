package com.lss.demo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Desc:
 * @authonr: LeeSongsheng
 * @create: 2020/07/05
 **/
public class queueDemo {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < 10; i++)
            queue.add(i);
        for (int i : queue)
            System.out.print(i + " ");
        System.out.println();
        
    }
}
