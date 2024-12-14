package com.demo.strings;

import com.google.common.base.Splitter;
import com.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.List;
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
            System.out.print(s + '-');
        }
        System.out.println();

        // 1-2-3-5-6-  会忽略中间空串
        String[] split1 = StringUtils.split("1|2|3||5|6", "|");
        for (String s : split1) {
            System.out.print(s + '-');
        }
        System.out.println();


        System.out.println("1234".substring(0, 3)); // 123
        // System.out.println("12".substring(0, 3));   //Exception in thread "main" java.lang.StringIndexOutOfBoundsException: begin 0, end 3, length 2
        // System.out.println("".substring(0, 3));     //Exception in thread "main" java.lang.StringIndexOutOfBoundsException: begin 0, end 3, length 0

        System.out.println(StringUtils.substring("1234", 0, 3));    // 123
        System.out.println(StringUtils.substring("12", 0, 3));      // 12
        System.out.println(StringUtils.substring("", 0, 3));        //""
        System.out.println(StringUtils.substring(null, 0, 3));      // null
        System.out.println(Optional.ofNullable(StringUtils.substring(null, 0, 3)).orElse(""));  //""

        // 1_2___4
        String join = StringUtils.join(new Object[]{"1", "2", "", null, "4"}, '_');
        System.out.println(join);

        String[] splits = StringUtils.split("1_2___4", "_");
        System.out.println(splits.length);
        System.out.println(JsonUtil.toJson(splits));
        // 3
        // ["1","2","4"]

        // 转为数组，保留空串
        String[] split2 = StringUtils.splitByWholeSeparatorPreserveAllTokens("1_2___4", "_");
        System.out.println(split2.length); // 5
        System.out.println(JsonUtil.toJson(split2)); // ["1","2","","","4"]

        // 转为list，保留空串
        List<String> split3 = Splitter.on("_").splitToList("1_2___4");
        // 直接打印list类型，元素不加引号
        System.out.println(split3);   //[1, 2, , , 4]
        // 使用JsonUtil.toJson序列化，元素加引号
        System.out.println(JsonUtil.toJson(split3)); // ["1","2","","","4"]

        Splitter splitter = Splitter.on(",").omitEmptyStrings().trimResults();
        System.out.println(splitter);
        // Splitter 拆分null时直接抛异常
        // System.out.println(splitter.split(null));
    }
}
