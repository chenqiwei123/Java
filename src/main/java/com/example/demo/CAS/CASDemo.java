package com.example.demo.CAS;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS 比较并交换=>CompareAndSwap 他是一条CPU并发原语
 * 总结下:
 * CAS(CompareAndSwap)
 *      比较当前工作内存的值与主内存的值,如果相同则执行规定操作,
 *      否则继续比较直到内存和工作内存的值一致为止.
 * CAS应用:
 *      CAS有三个操作数,内存值V,旧的预期值A,要修改的更新值B
 *      当且仅当预期值A和内存值V相同时,将内存值V修改为B,否则什么都不做
 * CAS缺点:
 *      CAS长时间一直不成功,可能会给CPU带来很大的开销
 *      只能保证一个共享变量的原子操作
 *      引来ABA问题
 * CAS-->Unsafe -->CAS的底层思想-->ABA--->原子引用更新-->如何规避ABA问题
 * */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5, 2022) + "\t current data:" + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 1024) + "\t current data:" + atomicInteger.get());
        //底层用到额unsafe的getAddInt方法,该方法使用getIntVolatile()和compareAndSwapInt()使用了native本地方法.可以直接操作底层资源,使用CAS进行判断并交换
        atomicInteger.getAndIncrement();

    }
}
