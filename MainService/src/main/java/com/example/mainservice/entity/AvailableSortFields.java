package com.example.mainservice.entity;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum AvailableSortFields {
    ID("id"),
    CREATION_DATE("creationDate"),
    HEALTH("health"),
    HEART_COUNT("heartCount"),
    STARSHIP_ID("starshipId");

    @Getter
    private final String name;

    AvailableSortFields(String name) {
        this.name = name;
    }

    public static boolean checkContains(String name) {
        List<String> collect = Stream.of(AvailableSortFields.values()).map(AvailableSortFields::getName).collect(Collectors.toList());
        return collect.contains(name);
    }

    public static AvailableSortFields getByName(String name) {
        List<AvailableSortFields> collect = Stream.of(AvailableSortFields.values()).collect(Collectors.toList());
        for (AvailableSortFields field : collect) {
            if (field.getName().equals(name)) return field;
        }
        return null;
    }
}
