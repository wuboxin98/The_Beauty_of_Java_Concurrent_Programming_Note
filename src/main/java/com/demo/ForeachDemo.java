package com.demo;

public class ForeachDemo {

    public static void main(String[] args) {
        System.out.println("Hello world!");

        int[] arr = {1, 2, 3, 4, 5};

        for (int i : arr) {
            System.out.println(i);
        }

        // foreach 循环不能对 null 进行遍历
        // for (int i : null) {
        //     System.out.println(i);
        // }
    }
}
