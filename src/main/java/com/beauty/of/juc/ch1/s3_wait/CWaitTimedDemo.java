package com.beauty.of.juc.ch1.s3_wait;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CWaitTimedDemo {
    public static void main(String[] args) {
        Object obj = new Object();
        // 线程1：每0.1秒打印一个计数时间，持续6秒
        // 线程2：同上，先打印两秒，然后等待3秒，再打印1秒
        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                try {
                    Thread.sleep(100);
                    System.out.println("t1: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss:SSS")));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t1: count end");
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                try {
                    Thread.sleep(100);
                    System.out.println("t2: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss:SSS")));
                    if (i == 20) {
                        System.out.println("t2: wait");
                        synchronized (obj) {
                            obj.wait(3000);
                        }
                        System.out.println("t2: wait end");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t2: count end");
        }).start();

    }
}
