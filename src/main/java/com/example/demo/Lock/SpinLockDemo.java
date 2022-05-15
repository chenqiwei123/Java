package com.example.demo.Lock;

import lombok.val;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class SpinLockDemo {
    //原子引用线程
    AtomicReference<Thread> atomicReference=new AtomicReference<>();
    public void MyLock(){
        Thread thread=Thread.currentThread();
        while (!atomicReference.compareAndSet(null,thread)) {

        }
        System.out.println(Thread.currentThread().getName()+"\t"+"加锁!!");
    }
    public void UnLock(){
        Thread thread=Thread.currentThread();
        while (!atomicReference.compareAndSet(thread,null)){

        }
        System.out.println(Thread.currentThread().getName()+"\t"+"解锁!!");
    }
    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(()->{
           spinLockDemo.MyLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.UnLock();
        },"t1").start();
        new Thread(()->{
            spinLockDemo.MyLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.UnLock();
        },"t2").start();

    }
}
