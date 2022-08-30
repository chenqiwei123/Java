package com.example.demo.Lock;

import lombok.val;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//可重入锁: synchronize和ReentrantLock
//自旋锁: 一直循环的方式去获取锁
class Phone {
    public synchronized void snendMessage() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"\t"+"发送短息");
        snendMEmail();

    }
    public synchronized void snendMEmail() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        System.out.println(Thread.currentThread().getName()+"\t"+"发送邮件");
    }
}

public class LockExample {
    public static void main(String[] args) {

        //非公平锁和公平锁
        //      非公平锁:多个线程去尝试拿到锁,获取不到旧处于队列中等待,能获取到直接获取该锁.
        //      公平锁:请求资源.若是第一个申请请求该资源立马获取这个资源否则进行队列中等待,按照FIFO的规则进行获取锁的资源
        //sync = fair ? new FairSync() : new NonfairSync();
        //        Lock lock = new ReentrantLock(false);

        //可重入锁(又名递归锁)  同一线程外层函数获得锁之后,内层递归函数仍然获得该锁的代码,
        //                          在同一线程外层函数获取该锁,内层函数自然也会自动获取该锁
        //        线程可以进入任何一个它已经拥有的锁同步着的代码块
        Phone phone = new Phone();
        new Thread(()->{
            try {
                System.out.println("======t1==========");
                phone.snendMessage();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();
        new Thread(()->{
            try {
                System.out.println("======t2==========");
                phone.snendMessage();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();

    }
}
