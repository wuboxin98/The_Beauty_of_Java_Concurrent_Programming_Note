package com.demo.strings;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public class StringUtilsDemo {

    public static void main(String[] args) {
        // 1|2|3|4|5|
        System.out.println(StringUtils.join(new Object[]{"1", "2", "3", "4", "5", ""}, "|"));
        // 1|2|3||5|6
        System.out.println(StringUtils.join(new Object[]{"1", "2", "3", "", "5", "6"}, "|"));
        System.out.println();

        // 1-2-3-4-5-  会忽略尾部空串
        String[] split = StringUtils.split("1|2|3|4|5|", "|");
        for (String s : split) {
            System.out.print(s+'-');
        }
        System.out.println();

        // 1-2-3-5-6-  会忽略中间空串
        String[] split1 = StringUtils.split("1|2|3||5|6", "|");
        for (String s : split1) {
            System.out.print(s+'-');
        }
        System.out.println();


        System.out.println("1234".substring(0, 3)); //123
        // System.out.println("12".substring(0, 3));   //Exception in thread "main" java.lang.StringIndexOutOfBoundsException: begin 0, end 3, length 2
        // System.out.println("".substring(0, 3));     //Exception in thread "main" java.lang.StringIndexOutOfBoundsException: begin 0, end 3, length 0

        System.out.println(StringUtils.substring("1234", 0, 3));    //123
        System.out.println(StringUtils.substring("12", 0, 3));      //12
        System.out.println(StringUtils.substring("", 0, 3));        //""
        System.out.println(StringUtils.substring(null, 0, 3));      //null
        System.out.println(Optional.ofNullable(StringUtils.substring(null, 0, 3)).orElse(""));  //""


    }
}
