package com.demo.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class FastjsonExample {
    public static void main(String[] args) {
        // 示例 JSON 字符串
        String jsonString = """
                {
                    "name": "Kimi",
                    "age": 25,
                    "hobbies": [
                        {"activity": "reading", "level": "advanced"},
                        {"activity": "coding", "level": "expert"}
                    ]
                }
                """;

        // 将 JSON 字符串解析为 JSONObject
        JSONObject jsonObject = JSON.parseObject(jsonString);

        // 获取数组对象
        JSONArray hobbiesArray = jsonObject.getJSONArray("hobbies");

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