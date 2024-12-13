package com.demo.basic_type;

public class IntegerDemo {
    public static void main(String[] args) {

        // Integer.valueOf(null) 会抛出空指针异常
        // System.out.println(Integer.valueOf(null));

        //  Integer.valueOf("") 会抛出数字格式异常
        // Exception in thread "main" java.lang.NumberFormatException: For input string: ""
        // 	at java.base/java.lang.NumberFormatException.forInputString(NumberFormatException.java:67)
        System.out.println(Integer.valueOf(""));


        // 包装类型作为参数时，形参的改变不会影响实参
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
