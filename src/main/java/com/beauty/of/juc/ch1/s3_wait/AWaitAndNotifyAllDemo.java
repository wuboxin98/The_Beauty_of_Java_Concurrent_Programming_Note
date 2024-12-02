package com.beauty.of.juc.ch1.s3_wait;

public class AWaitAndNotifyAllDemo {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        // wait 方法，会让一个线程放弃当前占有的锁，并阻塞
        // 需要等待 另一个线程调用 notify 或者 notifyAll 方法，才能继续执行

        // 现在我需要两个线程，一个线程A先运行3秒，然后执行 wait 方法阻塞，另一个线程B运行6秒后执行 notify 方法唤醒A。

        Object obj = new Object();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 6; i++) {
                try {
                    Thread.sleep(1000);
                    System.out.println("t1: " + i);
                    if (i == 2) {
                        synchronized (obj) {
                            obj.wait();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 6; i++) {
                try {
                    Thread.sleep(1000);
                    System.out.println("t2: " + i);
                    if (i == 5) {
                        synchronized (obj) {
                            obj.notify();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
    }
}
