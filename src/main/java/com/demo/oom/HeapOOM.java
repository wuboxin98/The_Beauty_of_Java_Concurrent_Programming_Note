package com.demo.oom;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class HeapOOM {
    static class OOMObject {
        // 可以添加一些属性，让对象占用更多内存
        private byte[] placeholder = new byte[640 * 1024]; // 每个对象占用640KB内存
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("main方法");
        Thread thread = new Thread(() -> {
            ArrayList<OOMObject> list = new ArrayList<>();
            while (true) {
                list.add(new OOMObject());
            }
        });
        thread.setDaemon(true);
        thread.start();
        thread.join();
        System.out.println("main方法结束");
    }
}
