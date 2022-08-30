package com.example.demo.Lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 通常用于那些资源有明确访问数量限制的场景，常用于限流 。
 *
 * 比如：数据库连接池，同时进行连接的线程有数量限制，连接不能超过一定的数量，当连接达到了限制数量后，后面的线程只能排队等前面的线程释放了数据库连接才能获得数据库连接。
 *
 * 比如：停车场场景，车位数量有限，同时只能容纳多少台车，车位满了之后只有等里面的车离开停车场外面的车才可以进入。
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 6; i++) {
            final int temp_i=i;
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(temp_i+"\t 车位被占用!");
                    try {
                        TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                    System.out.println(temp_i+"\t 车位被释放!");
                }
            },String.valueOf(i)).start();
        }
    }
}
