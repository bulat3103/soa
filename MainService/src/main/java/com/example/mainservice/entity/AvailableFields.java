package com.example.mainservice.entity;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum AvailableFields {
    ID("id"),
    NAME("name"),
    COORDINATES_X("coordinatesX"),
    COORDINATES_Y("coordinatesY"),
    HEALTH("health"),
    HEART_COUNT("heartCount"),
    ACHIEVEMENTS("achievements"),
    CATEGORY("category"),
    CHAPTER_NAME("chapterName"),
    CHAPTER_MARINES_COUNT("chapterMarinesCount"),
    STARSHIP_ID("starshipId"),
    STARSHIP_NAME("starshipName"),
    STARSHIP_COORDINATES_X("starshipCoordinatesX"),
    STARSHIP_COORDINATES_Y("starshipCoordinatesY"),
    STARSHIP_CREW_COUNT("starshipCrewCount"),
    STARSHIP_HEALTH("starshipHealth");

    @Getter
    private final String name;

    AvailableFields(String name) {
        this.name = name;
    }

    public static boolean checkContains(String name) {
        List<String> collect = Stream.of(AvailableFields.values()).map(AvailableFields::getName).collect(Collectors.toList());
        return collect.contains(name);
    }

    public static AvailableFields getByName(String name) {
        List<AvailableFields> collect = Stream.of(AvailableFields.values()).collect(Collectors.toList());
        for (AvailableFields field : collect) {
            if (field.getName().equals(name)) return field;
        }
        return null;
    }
}
