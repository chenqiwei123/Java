package com.example.demo.Lock;

import lombok.Getter;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatchDemo count=new CountDownLatchDemo();
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 过被灭亡了!!!");
                countDownLatch.countDown();
            }, CountryEnum.forEach_CountryEnum(i).getName()).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t 最后秦国统一大地完成!");

    }
}
