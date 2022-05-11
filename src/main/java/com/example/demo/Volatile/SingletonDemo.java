package com.example.demo.Volatile;

public class SingletonDemo {
    private static volatile SingletonDemo instance=null;
    private  SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+"\t 我是构造方法SingletonDemo()");
    }
    private static SingletonDemo getInstance(){
        //可能存在指令重排,引用对象分为三步:1.分配对象内存空间 2.初始化对象 3.设置instance指向刚分配的内存地址,此时instance!=null

        //DCL模式 (Double Check Lock)双重检查锁,有概率会出现指令重排
        if (instance==null){
            synchronized (SingletonDemo.class){
                if (instance==null){
                    instance=new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        //单线程(main线程的操作动作....)
//        System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());
        Long l = System.currentTimeMillis();
        for (int i = 1; i <= 100; i++) {
            new Thread(()->{
                SingletonDemo.getInstance();
            },String.valueOf(i)).start();
        }
        while (Thread.activeCount()>2){

        }
        System.out.println("生成消耗的时间:"+String.valueOf(System.currentTimeMillis()-l));
    }
}
