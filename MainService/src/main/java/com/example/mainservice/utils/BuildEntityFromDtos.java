package com.example.mainservice.utils;

import com.example.mainservice.entity.SpaceMarine;
import com.example.mainservice.model.request.SpaceMarineBuildDto;

import java.time.Instant;

public class BuildEntityFromDtos {

    public static void buildCommonSpaceMarineFields(SpaceMarine spaceMarine, SpaceMarineBuildDto spaceMarineBuildDto) {
        spaceMarine.setName(spaceMarineBuildDto.getName());
        spaceMarine.setCoordinateX(spaceMarineBuildDto.getCoordinates().getX());
        spaceMarine.setCoordinateY(spaceMarineBuildDto.getCoordinates().getY());
        spaceMarine.setHealth(spaceMarineBuildDto.getHealth());
        spaceMarine.setHeartCount(spaceMarineBuildDto.getHeartCount());
        spaceMarine.setAchievements(spaceMarineBuildDto.getAchievements());
        spaceMarine.setCategory(spaceMarineBuildDto.getCategory());
        spaceMarine.setChapterName(spaceMarineBuildDto.getChapter().getName());
        spaceMarine.setChapterSpaceMarinesCount(spaceMarineBuildDto.getChapter().getSpaceMarinesCount());
    }
    public static SpaceMarine buildSpaceMarine(SpaceMarineBuildDto spaceMarineBuildDto) {
        SpaceMarine spaceMarine = new SpaceMarine();
        buildCommonSpaceMarineFields(spaceMarine, spaceMarineBuildDto);
        spaceMarine.setCreationDate(Instant.now());
        return spaceMarine;
    }
}
