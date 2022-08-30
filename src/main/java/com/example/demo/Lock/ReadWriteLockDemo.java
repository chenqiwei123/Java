package com.example.demo.Lock;

import lombok.val;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 缓存的三大步:写入缓存,读取缓存,清空缓存
 */
class MyCache{
    private volatile Map<String,Object> map=new HashMap<>();
    private Lock lock=new ReentrantLock();
    private ReentrantReadWriteLock reentrantReadWriteLock=new ReentrantReadWriteLock();

    public void put(String key,Object value){
        reentrantReadWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 正在写入:"+key);
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t 写入完成: ");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            reentrantReadWriteLock.writeLock().unlock();
        }
    }
    public void get(String key){
        reentrantReadWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 正在读取:");
            try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {e.printStackTrace();}
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读取完成: "+o);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            reentrantReadWriteLock.readLock().unlock();
        }
    }
    public void clear(){
        map.clear();
    }

}

/**
 * 多个线程同时读一个资源类没有任何问题,所以为了满足并发量,读取共享源应该可以同时进行,读取同时有需要去写的操作就无法操作
 * 总结:
 *      读-读 能共存
 *      读-写 不能共存
 *      写-写 不能共存
 * 写操作:原子+独占
 */

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache=new MyCache();
        for (int i = 0; i < 5; i++) {
            final int tempInt=i;
            new Thread(()->{
                myCache.put(tempInt+"",tempInt+"");
            },String.valueOf(tempInt)+"Read").start();
        }
        try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {e.printStackTrace();}
        for (int j = 0; j < 5; j++) {
            final int tempInt=j;
            new Thread(()->{
                myCache.get(tempInt+"");
            },String.valueOf(tempInt)+"Write").start();
        }
    }
}
