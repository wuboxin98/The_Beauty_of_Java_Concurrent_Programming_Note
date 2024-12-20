package com.demo.guava;

import com.google.common.collect.Range;
import org.joda.time.LocalDate;

public class RangeDemo {


    public static void main(String[] args) {
        Range<LocalDate> range = Range.closed(LocalDate.now().minusDays(1), LocalDate.now());

        System.out.println(range);
    }
}
