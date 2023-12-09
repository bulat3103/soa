package com.example.mainservice.utils;

/*import com.example.mainservice.catalog.CreateSpaceMarineRequest;
import com.example.mainservice.entity.SpaceMarine;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class BuildEntityFromDtos {

    public static void buildCommonSpaceMarineFields(SpaceMarine spaceMarine, CreateSpaceMarineRequest createSpaceMarineRequest) {
        spaceMarine.setName(createSpaceMarineRequest.getName());
        spaceMarine.setCoordinateX(Long.parseLong(createSpaceMarineRequest.getCoordinates().getX()));
        spaceMarine.setCoordinateY(Double.parseDouble(createSpaceMarineRequest.getCoordinates().getY()));
        spaceMarine.setHealth(Double.parseDouble(createSpaceMarineRequest.getHealth()));
        spaceMarine.setHeartCount(Integer.parseInt(createSpaceMarineRequest.getHeartCount()));
        spaceMarine.setAchievements(createSpaceMarineRequest.getAchievements());
        spaceMarine.setCategory(createSpaceMarineRequest.getCategory().toUpperCase());
        spaceMarine.setChapterName(createSpaceMarineRequest.getChapter().getName());
        spaceMarine.setChapterMarinesCount(Integer.parseInt(createSpaceMarineRequest.getChapter().getMarinesCount()));
    }
    public static SpaceMarine buildSpaceMarine(CreateSpaceMarineRequest createSpaceMarineRequest) {
        SpaceMarine spaceMarine = new SpaceMarine();
        buildCommonSpaceMarineFields(spaceMarine, createSpaceMarineRequest);
        spaceMarine.setCreationDate(ZonedDateTime.now(ZoneOffset.UTC));
        return spaceMarine;
    }
}*/
