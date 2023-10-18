package com.example.mainservice.model;

import com.example.mainservice.entity.FilterOperation;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public enum AstartesCategory {
    AGGRESSOR,
    ASSAULT,
    INCEPTOR,
    TERMINATOR,
    UNDEFINED;

    public static AstartesCategory getFilter(String value, FilterOperation op) {
        if (op.equals(FilterOperation.EQ) || op.equals(FilterOperation.NE)) {
            return Arrays.stream(AstartesCategory.values())
                    .filter(e -> Objects.equals(e.toString().toLowerCase(), value.toLowerCase()))
                    .findFirst()
                    .orElse(UNDEFINED);
        } else if (op.equals(FilterOperation.LT) || op.equals(FilterOperation.LTE)) {
            List<AstartesCategory> collect = Arrays.stream(AstartesCategory.values())
                    .filter(e -> e.toString().toLowerCase().compareTo(value.toLowerCase()) <= 0)
                    .collect(Collectors.toList());
            if (collect.size() == 0) return UNDEFINED;
            return collect.get(collect.size() - 1);
        } else {
            return Arrays.stream(AstartesCategory.values())
                    .filter(e -> e.toString().toLowerCase().compareTo(value.toLowerCase()) >= 0)
                    .findFirst()
                    .orElse(UNDEFINED);
        }
    }
}
