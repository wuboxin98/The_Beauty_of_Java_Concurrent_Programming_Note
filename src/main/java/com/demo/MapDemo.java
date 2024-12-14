package com.demo;

import com.google.common.base.Splitter;
import com.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Map;

public class MapDemo {
    public static void main(String[] args) {
        Map<String, String> map = Map.of("name", "张三", "age", "18");

        // 获得一个不存在的key 会返回 null
        System.out.println(map.get("1"));
        // 默认打印为全部内容 {name=张三, age=18}
        System.out.println(map);
    }
}
