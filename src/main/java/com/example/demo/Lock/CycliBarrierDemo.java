package com.example.demo.Lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CycliBarrierDemo {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(7,()->{
            System.out.println("我要去找七彩颜色!");
        });
        for (int i = 1; i <=7; i++) {
            final int temp_i=i;
            new Thread(()->{
                System.out.println("收集到第"+temp_i+"");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },"input Thread name").start();
        }
        cyclicBarrier.await();
        System.out.println("七彩颜色集齐了!!");
    }
}
