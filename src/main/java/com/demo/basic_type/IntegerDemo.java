package com.demo.basic_type;

public class IntegerDemo {
    public static void main(String[] args) {


        Integer a = 12;
        System.out.println("main:" + a);
        multipleTwo(a);
        System.out.println("main:" + a);
    }

    public static void multipleTwo(Integer a) {
        a = a * 2;
        System.out.println("multipleTwo:" + a);
    }
}
