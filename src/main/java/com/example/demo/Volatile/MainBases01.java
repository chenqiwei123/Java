package com.example.demo.Volatile;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * volatile 是java虚拟机提供的轻量级的同步机制
 * volatile 有三大特性: 1.保证可见性    一个线程更新主内存的变量后,同时通知其他线程重新读取该变量值
 *                    2.不保证原子性  不可分割,某个线程正在做某个业务时,中间不可以被加塞或分割,需要整理完整,要么同时成功,要么同时失败
 *                    3.禁止指令重排
 * <p>
 * JMM 有三大特性: 1.可见性
 *               2.原子性
 *               3.有序性
 * <p>
 * JMM内存模型规定:
 * 1.线程加锁前,必须读取主内存的最新值到工作内存中
 * 2.线程解锁前,必须把共享变量的值刷新到主内存中
 * 3.加锁解锁是同一把锁
 */

class MyData {
    volatile int number = 0;
    AtomicInteger atomicInteger=new AtomicInteger();
    public void AddNumber() {
        this.number = 60;
    }
    public void AddPlus() {
        this.number++;
    }
    public void AddAtomicInteger(){
        atomicInteger.getAndIncrement();
    }
}

@SuppressWarnings("StatementWithEmptyBody")
public class MainBases01 {

    public static void main(String[] args) throws InterruptedException {
//        VolatileAtom();
        seeOkByVolatile();
    }


    /**
     * volatile的不保证原子性测试
     * 解决原子性方案
     * 1.加sync
     * 2.使用juc下的AtomicInteger
     */
    public static void VolatileAtom() {
        MyData myData = new MyData();
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    myData.AddPlus();
                    myData.AddAtomicInteger();
                }
            },String.valueOf(i)).start();
        }
        while (Thread.activeCount()>2){//一个main线程,另一个是GC线程
            Thread.yield();
        }
        System.out.println("验证volatile的原子性"+myData.number);
        System.out.println("AtomicInteger的原子性"+myData.atomicInteger);
    }

    /**
     * 可以保持volatile的可见性,及时通知其他线程,主物理内存的值已经被修改
     */
    public static void seeOkByVolatile() {
        MyData myData = new MyData();
//        第一个线程
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in!  myData.number=" + myData.number);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.AddNumber();
            System.out.println(Thread.currentThread().getName() + "\t AddNumber()!  myData.number=" + myData.number);
        }, "AAA").start();

        //可以另起一个线程
//        new Thread(()->{
//            System.out.println(Thread.currentThread().getName()+"\t come in!  myData.number="+myData.number);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName()+"\t myData.AddNumber()!  myData.number="+myData.number);
//        },"BBB").start();
//        在main线程进行验证,没有人通知main线程
        while (myData.number == 0) {
        }
        System.out.println(Thread.currentThread().getName() + "\t myData.number=" + myData.number);
    }


}
