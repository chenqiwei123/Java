package com.example.demo.ContainerNotSafe;

import lombok.val;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;

/**
 * ArrayList
 * java.unit.ConcurrentModificationException
 * 故障现象
 * --  出现java.unit.ConcurrentModificationException
 * 导致原因
 * --  没有进行加锁,出现插入显示异常
 * --  并发争抢修改导致
 * 解决方案
 * --  new Vector();
 * --  Collections.synchronizedList(new ArrayList<>());
 * 优化建议
 *
 */
public class ContainerNotSafeDemo {
    public static void main(String[] args) {
//        Map map = new HashMap<>();
//        Map map = new ConcurrentHashMap();
        Map map = Collections.synchronizedMap(new HashMap<>());
//        map.forEach(System.out::println);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }

    /**
     * HashSet底层是HashMap
     * new HashSet<>().add("");只关心key value为空对象
     */
    private static void HashSet() {
        //        Set set=new  HashSet<>();
//        Set set=Collections.synchronizedSet(new HashSet<>());
        Set set=new CopyOnWriteArraySet<>();
        set.forEach(System.out::println);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },"t1").start();
        }
    }

    /**
     * ArrayList线程不安全的示例及解决办法
     */
    private static void ArrayList() {
        //        List list= new Vector();
//        List list= Collections.synchronizedList(new ArrayList<>());
        List list=new  CopyOnWriteArrayList<>();
        list.forEach(System.out::println);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },"t1").start();
        }
    }
}
