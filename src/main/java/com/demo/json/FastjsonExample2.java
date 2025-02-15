package com.demo.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

public class FastjsonExample2 {
    public static void main(String[] args) {
        // 示例 JSON 字符串
        String jsonString = """
                {
                    "name": "Kimi",
                    "age": 25,
                    "hobbies": [
                    ]
                }
                """;

        // 将 JSON 字符串解析为 JSONObject
        Map<String, String> jsonMap = JSON.parseObject(jsonString, new TypeReference<Map<String, String>>(){});

        // 获取数组对象
        String hobbiesArrayStr = jsonMap.get("hobbies");
        JSONArray hobbiesArray = JSON.parseArray(hobbiesArrayStr);

        System.out.println("hobbiesArray size:" + hobbiesArray.size());
        // 遍历数组对象
        for (int i = 0; i < hobbiesArray.size(); i++) {
            // 获取数组中的每个对象
            JSONObject hobby = hobbiesArray.getJSONObject(i);

            // 获取对象中的字段
            String activity = hobby.getString("activity");
            String level = hobby.getString("level");

            // 输出结果
            System.out.println("Activity: " + activity + ", Level: " + level);
        }
    }
}