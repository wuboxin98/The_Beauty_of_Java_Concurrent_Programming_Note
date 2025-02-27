package com.demo.date;

import java.time.LocalDate;
import java.util.Date;

public class DateExample {
    public static void main(String[] args) {
        LocalDate today = LocalDate.of(2025,1,30);
        LocalDate pastDate = today.minusDays(30);
        System.out.println("Today's Date: " + today);
        System.out.println("Date 30 Days Ago: " + pastDate);

        Date date = new Date();
        System.out.println(date);

    }
}