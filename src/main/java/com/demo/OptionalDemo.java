package com.demo;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.ListUtils;

import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

public class OptionalDemo {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        List<String> emptyList = Lists.newArrayList();
        System.out.println(String.join(",", emptyList));

        List<String> nullList = List.of("a", "b", "c");
        System.out.println(String.join(",", nullList));

        nullList = null;  // String.join中直接传null容器会报错，需要转为一个空的list
        System.out.println("使用Optional，预防null：");
        System.out.println(String.join(",", Optional.ofNullable(nullList).orElse(List.of())));
        // 但是 List.of() 是 Java9 的特性，不适用于 Java8 的项目


        // 如果是 Java8，可以使用 apache 的 ListUtils
        System.out.println("使用apache的ListUtils，预防null：");
        System.out.println(String.join(",", ListUtils.emptyIfNull(nullList)));

        StringJoiner joiner = new StringJoiner(",");
        System.out.println(joiner.add("a").add("b").add("c"));

        joiner = new StringJoiner(",", "[", "]");
        System.out.println(joiner.add("a").add("b").add("c"));

        /**
         为了避免 list 的潜在空指针错误，可以对参数进行以下一些处理：
         1. 逻辑判断，判断是否为 null，然后赋值一个空的 List
         CollectionUtils.isNotEmpty(nullList) ? nullList : new ArrayList<>()
         2. 使用 Optional 判断
         Optional.ofNullable(nullList).orElse(List.of())
         其中 List.of() 是 Java9 的特性，不适用于 Java8 的项目
         3. 使用 apache 的 ListUtils 工具，更加简洁
         ListUtils.emptyIfNull(nullList)

         */


    }
}