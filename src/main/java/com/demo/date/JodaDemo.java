package com.demo.date;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class JodaDemo {

    public static final DateTimeFormatter shortDateFormatter = DateTimeFormat.forPattern("yyyyMMdd");
    public static final DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter dateTimeFormatter2 = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {

        // formatPrintTest();

        dateCalculate();
    }

    // -------------------------------------- 日期计算 -------------------------------------
    private static void dateCalculate() {
        // Joda-Time，当前日期
        LocalDate localDate = new LocalDate(2024,12,06);
        System.out.println(localDate);

        // 日期加减
        System.out.println(localDate.plusDays(1)); // 加一天
        System.out.println(localDate.plusMonths(1)); // 加一个月
        System.out.println(localDate.plusYears(1)); // 加一年

        System.out.println(localDate.minusDays(1)); // 减一天
        System.out.println(localDate.minusMonths(1)); // 减一个月
        System.out.println(localDate.minusYears(1)); // 减一年

        // 日期比较
        LocalDate localDate2 = new LocalDate(2021, 8, 20);
        System.out.println(localDate.isAfter(localDate2)); // true
        System.out.println(localDate.isBefore(localDate2)); // false
        System.out.println(localDate.isEqual(localDate2)); // false

        // 计算日期的天数之差
        LocalDate localDate3 = dateFormatter.parseLocalDate("2021-08-20");
        LocalDate localDate4 = dateFormatter.parseLocalDate("2021-09-20");
        System.out.println(Days.daysBetween(localDate3, localDate3).getDays()); // 0
        System.out.println(Days.daysBetween(localDate3, localDate4).getDays()); // 31
        System.out.println(Days.daysBetween(localDate4, localDate3).getDays()); // -31
    }

    // -----------------------------------------格式化输出-------------------------------------
    private static void formatPrintTest() {

        // Joda-Time，默认格式就是 2024-12-06
        LocalDate localDate = new LocalDate();
        System.out.println(localDate);
        System.out.println(dateFormatter.print(localDate)); // 2024-12-06
        System.out.println(shortDateFormatter.print(localDate));  // 20241206


        // 构建指定日志的几种方式，以下输出都是一样的 2021-08-20
        // 1、构造器方式
        LocalDate localDate2 = new LocalDate(2021, 8, 20);
        System.out.println(localDate2);

        // 2、字符串解析方式，格式1
        LocalDate localDate3 = LocalDate.parse("2021-08-20", dateFormatter);
        System.out.println(localDate3);
        localDate3 = dateFormatter.parseLocalDate("2021-08-20");
        System.out.println(localDate3);

        // 2、字符串解析方式，格式2
        LocalDate localDate4 = LocalDate.parse("20210820", shortDateFormatter);
        System.out.println(localDate4);
        localDate4 = shortDateFormatter.parseLocalDate("20210820");
        System.out.println(localDate4);

    }
}
