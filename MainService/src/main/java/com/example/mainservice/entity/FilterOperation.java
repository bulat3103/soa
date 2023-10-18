package com.example.mainservice.entity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum FilterOperation {
    EQ,
    NE,
    GT,
    LT,
    LTE,
    GTE;

    public static boolean checkContains(String name) {
        List<String> collect = Stream.of(FilterOperation.values())
                .map(Enum::toString)
                .collect(Collectors.toList());
        return collect.contains(name);
    }
}
