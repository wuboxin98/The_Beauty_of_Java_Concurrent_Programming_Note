package com.beauty.of.juc.ch1.s2_thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 新建线程的几种方式
 */
public class ThreeWay2Thread {

    // 1. 继承Thread类
    static class MyThread1 extends Thread {
        @Override
        public void run() {
            System.out.println("MyThread1 is running");
        }
    }

    // 2. 实现Runnable接口
    static class MyThread2 implements Runnable {
        @Override
        public void run() {
            System.out.println("MyThread2 is running");
        }
    }

    // 3. 使用Callable和FutureTask
    static class MyThread3 implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("MyThread3 is running");
            return "MyThread3 result";
        }
    }



    public static void main(String[] args) {
        // 方法1
        MyThread1 myThread1 = new MyThread1();
        myThread1.start();

        // 方法2
        Thread myThread2 = new Thread(new MyThread2());
        myThread2.start();

        // 方法3
        FutureTask<String> stringFutureTask = new FutureTask<>(new MyThread3());
        Thread myThread3 = new Thread(stringFutureTask);
        myThread3.start();
        try {
            System.out.println(stringFutureTask.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        // 申明线程的方式有很多，基于 Thread 父类，Runnable 接口、Callable 接口和 FutureTask接口，以及线程池、CompletableFuture 等。
        // 不过创建线程其实都是基于 thread 的 start 方法实现的，只有调用该方法之后，系统才会分配线程资源。
    }

}
