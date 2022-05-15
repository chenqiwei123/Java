package com.example.demo.CAS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {
    public static void main(String[] args) {
        System.out.println("================以下是ABA问题产生============");
        AtomicReference<Integer> atomicReference = new AtomicReference(100);
        new Thread(() -> {
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        }, "t1").start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("", atomicReference.compareAndSet(100, 2019) + "\t" + atomicReference.toString());
        }, "t2").start();

        System.out.println("=============以下是ABA问日解决==============");
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);
        new Thread(() -> {
            int getAtomicStamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t 第一次版本号:" + atomicStampedReference.getStamp());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t 第二次版本号:" + atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t 第三次版本号:" + atomicStampedReference.getStamp());
        }, "t3").start();
        new Thread(() -> {
            int getAtomicStamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t 第1次版本号:" + atomicStampedReference.getStamp());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf(atomicStampedReference.compareAndSet(100, 2019, getAtomicStamp, getAtomicStamp + 1) + "\t" + atomicStampedReference.getReference().toString());
            System.out.println(Thread.currentThread().getName() + "\t 第2次版本号:" + atomicStampedReference.getStamp());
        }, "t4").start();
    }
}
