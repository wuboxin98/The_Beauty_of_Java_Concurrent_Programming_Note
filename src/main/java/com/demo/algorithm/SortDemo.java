package com.demo.algorithm;

import com.util.JsonUtil;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SortDemo {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(3, 4, 1, 5, 2);
        // 首先，直接打印list，只能看到list的引用地址
        System.out.println(list);
        // 这里推荐使用序列化工具
        // System.out.println(JsonUtil.toJson(list));

        // 排序
        list.sort((a, b) -> a - b);
        System.out.println(JsonUtil.toJson(list));

        // [3, 4, 1, 5, 2]  直接打印
        // [3,4,1,5,2]    JsonUtil.toJson序列号
        // [1,2,3,4,5]
        // 实验证明
        // 1、sort方法会改变原有容器的元素的顺序
        // 2、值得记住的是，默认排序是小端排序
        // 3、直接打印list，可以看到完整内容
    }
}
