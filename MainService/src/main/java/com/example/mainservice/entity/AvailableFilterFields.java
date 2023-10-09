package com.example.mainservice.entity;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum AvailableFilterFields {
    ID("id"),
    NAME("name"),
    HEALTH("health"),
    HEART_COUNT("heartCount"),
    ACHIEVEMENTS("achievements"),
    CATEGORY("category"),
    STARSHIP_ID("starshipId");

    @Getter
    private final String name;

    AvailableFilterFields(String name) {
        this.name = name;
    }

    public static boolean checkContains(String name) {
        List<String> collect = Stream.of(AvailableFilterFields.values()).map(AvailableFilterFields::getName).collect(Collectors.toList());
        return collect.contains(name);
    }

    public static AvailableFilterFields getByName(String name) {
        List<AvailableFilterFields> collect = Stream.of(AvailableFilterFields.values()).collect(Collectors.toList());
        for (AvailableFilterFields field : collect) {
            if (field.getName().equals(name)) return field;
        }
        return null;
    }
}
