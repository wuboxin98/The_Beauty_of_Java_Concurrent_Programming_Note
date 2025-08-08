package com.demo.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeExample {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");

        LocalDateTime today = LocalDateTime.now();
        System.out.println(today.format(formatter));

        String dateString = "2025-01-03_12:00:00";
        LocalDateTime parsedDateTime = LocalDateTime.parse(dateString, formatter);
        System.out.println("Parsed DateTime: " + parsedDateTime.format(formatter));

        Date date = new Date();
        System.out.println(date);

        System.out.println(String.format("Current Date: %tF %<tT", date));
        // System.out.println(String.format("Current Date: %s", "123"));
        System.out.println(String.format("","123123"));
    }
}