package com.example.mainservice.entity;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum AvailableFields {
    ID("id", null),
    NAME("name", null),
    COORDINATE_X("coordinateX", null),
    COORDINATE_Y("coordinateY", null),
    HEALTH("health", null),
    HEART_COUNT("heartCount", null),
    ACHIEVEMENTS("achievements", null),
    CATEGORY("category", null),
    CHAPTER_NAME("chapterName", null),
    CHAPTER_MARINES_COUNT("chapterMarinesCount", null),
    STARSHIP("starship", "id"),
    STARSHIP_NAME("starshipName", "name"),
    STARSHIP_COORDINATE_X("starshipCoordinateX", "coordinateX"),
    STARSHIP_COORDINATE_Y("starshipCoordinateY", "coordinateY"),
    STARSHIP_CREW_COUNT("starshipCrewCount", "crewCount"),
    STARSHIP_HEALTH("starshipHealth", "health");

    @Getter
    private final String name;
    @Getter
    private final String starshipTableName;

    AvailableFields(String name, String starshipTableName) {
        this.name = name;
        this.starshipTableName = starshipTableName;
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
