package com.demo.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JDK8LocalDateDemo {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        System.out.println(LocalDate.parse("2024-12-18", formatter).isBefore(LocalDate.parse("2024-12-17", formatter)));
        System.out.println(LocalDate.parse("2024-12-18", formatter).isBefore(LocalDate.parse("2024-12-18", formatter)));
        System.out.println(LocalDate.parse("2024-12-18", formatter).isBefore(LocalDate.parse("2024-12-19", formatter)));
    }
}
