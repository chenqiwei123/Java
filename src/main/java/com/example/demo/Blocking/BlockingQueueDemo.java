package com.example.demo.Blocking;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 阻塞队列
 * ArrayBlockingQueue: 是一个基于数组结构的有界阻塞队列,此队列按FIFO原则对元素及进行排序
 * LinkBlockingQueue: 一个基于链表结构的阻塞队列,每个插入操作必须等到另一个线程调用移除操作,否则插入操作一致处于阻塞的状态,吞吐量通常要高于ArrayBlockingQueue
 * SynchronousQueue: 一个不存储元素的阻塞队列,每个插入操作必须等到另一个线程调用移除操作,否则插入操作一致处于阻塞状态,吞吐量通常高于LinkBlockingQueue
 *
 * 1.队列
 *
 * 2.阻塞队列
 *      2.1 阻塞队列有没有好的一面
 *      2.2 不得不阻塞,你如何管理
 *
 * BlockingQueue:
 *          1.add()/remove()/element(): add()向队列添加元素,当队列满的时候抛出异常,remove()移除队列的头元素,当队列空的时候取抛出异常,element()获取队列的头元素,为空时抛出异常
 *          2.offer()/peek()/poll(): offer()向队列中添加元素,添加成功返回true,添加不成功/队列满了返回false,peek()获取队列的头部元素,队列为空那个返回null,pool()移除队列的头部元素,如果为空返回null
 *
 * */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
//        BlockingQueueAdd();
//        blockingQueueOffer();
        BlockingQueue<String> blockingQueue=new ArrayBlockingQueue<>(3);
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        System.out.println("==============================");
        blockingQueue.put("x");


    }

    private static void blockingQueueOffer() {
        BlockingQueue<String> blockingQueue=new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("x"));
        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }

    public static void BlockingQueueAdd() {
        BlockingQueue<String> blockingQueue=new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        blockingQueue.removeAll(blockingQueue);
        System.out.println(blockingQueue.add("d"));
        //取出队列的头元素,为空的时候抛出异常
        System.out.println(blockingQueue.element());
    }
}

