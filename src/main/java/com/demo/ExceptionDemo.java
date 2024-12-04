package com.demo;

public class ExceptionDemo {


    public static void main(String[] args) {
        System.out.println("Hello world!");
        ExceptionDemo2 exceptionDemo2 = new ExceptionDemo2();

        try {

            System.out.println("Before exceptionDemo2.test()");
            exceptionDemo2.test();
        } catch (Exception e) {
            System.out.println("Catch exception: " + e.getMessage());
        }
    }


    /**
     * 对于受检异常，需要在方法签名中声明，或者捕获并处理。
     * 对于非受检异常，可以不用声明，也可以捕获并处理。
     *
     * 受检异常包括所有继承自 Exception 的异常，但不包括继承自 RuntimeException 的异常。
     * 非受检异常包括继承自 RuntimeException 的运行异常，也包括继承 Throwable 的 Error。
     */
    public void testUnchecked() {
        throw new RuntimeException("ExceptionDemo: This is a runtime exception");
    }

    public void testChecked() throws Exception {
    // Code that might throw a checked exception
    throw new Exception("This is a checked exception");
}
}
