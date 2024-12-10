package com.demo.date;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DateFormaterDemo {

    public static final DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");


    public static void main(String[] args) {
        System.out.println("Hello world!");

        /**
         * 这里展示了两种格式化输出日期的方法
         */
        java.time.LocalDate localDate = LocalDate.now().minusDays(1);
        Date yesterday = java.sql.Date.valueOf(localDate);


        System.out.println("SimpleDateFormat: " + format.format(yesterday));

        System.out.println("DateFormatter: " + dateFormatter.print(yesterday.getTime()));
    }

}
