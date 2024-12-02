package com.beauty.of.juc.ch1.s3_wait;

public class BNotityAllDemo {
    public static void main(String[] args) throws InterruptedException {
        // 虽然一个对象的同步块一次只能被一个线程持有，但是一个对象同时可以被多个线程 wait。
        // 因为当前线程执行 wait 方法后，会释放锁，其他线程就可以进入同步块，再次执行 wait 方法。
        Object obj = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (obj) {
                try {
                    System.out.println("t1: wait");
                    obj.wait();
                    System.out.println("t1: notify");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (obj) {
                try {
                    System.out.println("t2: wait");
                    obj.wait();
                    System.out.println("t2: notify");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread t3 = new Thread(() -> {
            synchronized (obj) {
                try {
                    System.out.println("t3: notifyAll");
                    obj.notifyAll();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t1.start();
        t2.start();

        Thread.sleep(1000);

        t3.start();
    }
}
