package com.demo.strings;

import java.util.ArrayList;
import java.util.List;

public class JoinExample2 {
    public static void main(String[] args) {
        List<String> cities = new ArrayList<>();
        cities.add("New York");
        cities.add("London");
        cities.add("Tokyo");
        String joinedCities = String.join(" | ", cities);
        System.out.println(joinedCities);
    }
}