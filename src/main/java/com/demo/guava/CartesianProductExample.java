package com.demo.guava;


import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CartesianProductExample {
    public static void main(String[] args) {
        Set<String> set1 = Set.of("A", "B");
        Set<String> set2 = Set.of("1", "2");
        Set<String> set3 = Set.of("X", "Y");

        List<Set<String>> sets = Arrays.asList(set1, set2, set3);

        // List<List<String>> cartesianProduct = CollectionUtils.cartesianProduct(sets);

        // List<Set<String>> result = cartesianProduct.stream()
        //         .map(Set::copyOf)
        //         .collect(Collectors.toList());

        // result.forEach(System.out::println);
    }
}