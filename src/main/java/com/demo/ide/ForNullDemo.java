package com.demo.ide;

import java.util.List;

public class ForNullDemo {


    public static void main(String[] args) {
        List<String> list = null;
        for (String s : list) {
            System.out.println(s);
        }
    }
}
