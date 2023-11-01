package com.example.mainservice.utils;

import com.example.mainservice.entity.SpaceMarine;
import com.example.mainservice.model.AstartesCategory;
import com.example.mainservice.model.request.SpaceMarineBuildDto;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class BuildEntityFromDtos {

    public static void buildCommonSpaceMarineFields(SpaceMarine spaceMarine, SpaceMarineBuildDto spaceMarineBuildDto) {
        spaceMarine.setName(spaceMarineBuildDto.getName());
        spaceMarine.setCoordinateX(Long.parseLong(spaceMarineBuildDto.getCoordinates().getX()));
        spaceMarine.setCoordinateY(Double.parseDouble(spaceMarineBuildDto.getCoordinates().getY()));
        spaceMarine.setHealth(Double.parseDouble(spaceMarineBuildDto.getHealth()));
        spaceMarine.setHeartCount(Integer.parseInt(spaceMarineBuildDto.getHeartCount()));
        spaceMarine.setAchievements(spaceMarineBuildDto.getAchievements());
        spaceMarine.setCategory(spaceMarineBuildDto.getCategory().toUpperCase());
        spaceMarine.setChapterName(spaceMarineBuildDto.getChapter().getName());
        spaceMarine.setChapterMarinesCount(Integer.parseInt(spaceMarineBuildDto.getChapter().getMarinesCount()));
    }
    public static SpaceMarine buildSpaceMarine(SpaceMarineBuildDto spaceMarineBuildDto) {
        SpaceMarine spaceMarine = new SpaceMarine();
        buildCommonSpaceMarineFields(spaceMarine, spaceMarineBuildDto);
        spaceMarine.setCreationDate(ZonedDateTime.now(ZoneOffset.UTC));
        return spaceMarine;
    }
}
