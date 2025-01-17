package com.demo.ide;

import java.util.Random;

public class NullDemo {

    public static void main(String[] args) throws InterruptedException {

        Random random = new Random();
        Object object, temp = new Object();
        while (true) {
            if (random.nextInt(100) < 30) {
                object = null;
            } else {
                object = temp;
            }

            if (object != null) {
                if (random.nextInt(100) < 70) {
                    continue;
                }
                System.out.println(object);
            }
            System.out.println(object == null);
            Thread.sleep(1000);
        }
    }
}
