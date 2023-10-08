package com.example.mainservice.services.interfaces;

import com.example.mainservice.entity.SpaceMarine;
import model.request.SpaceMarineBuildDto;
import model.response.SpaceMarineResponseDto;

import java.util.List;

public interface SpaceMarineService {
    SpaceMarineResponseDto createSpaceMarine(SpaceMarineBuildDto spaceMarineBuildDto);

    SpaceMarineResponseDto updateSpaceMarine(Long id, SpaceMarineBuildDto spaceMarineBuildDto);

    void deleteSpaceMarine(Long id);

    SpaceMarineResponseDto getSpaceMarineById(Long id);

    List<SpaceMarineResponseDto> getAllSpaceMarines(List<String> sort, List<String> filter, Integer page, Integer pageSize);
}
