package com.example.mainservice.services.interfaces;

import com.example.mainservice.model.Filter;
import com.example.mainservice.model.Sort;
import com.example.mainservice.model.request.SpaceMarineBuildDto;
import com.example.mainservice.model.response.SpaceMarineResponseDto;

import java.util.List;

public interface SpaceMarineService {
    SpaceMarineResponseDto createSpaceMarine(SpaceMarineBuildDto spaceMarineBuildDto);

    SpaceMarineResponseDto updateSpaceMarine(Long id, SpaceMarineBuildDto spaceMarineBuildDto);

    void deleteSpaceMarine(Long id);

    SpaceMarineResponseDto getSpaceMarineById(Long id);

    List<SpaceMarineResponseDto> getAllSpaceMarines(List<Sort> sort, List<Filter> filter, Integer page, Integer pageSize);
}
