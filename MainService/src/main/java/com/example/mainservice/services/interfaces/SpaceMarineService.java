package com.example.mainservice.services.interfaces;

import com.example.mainservice.catalog.CreateSpaceMarineRequest;
import com.example.mainservice.catalog.SpaceMarineResponseDto;
import com.example.mainservice.catalog.UpdateSpaceMarineRequest;
import com.example.mainservice.model.Filter;
import com.example.mainservice.model.Sort;

import java.util.List;

public interface SpaceMarineService {
    SpaceMarineResponseDto createSpaceMarine(CreateSpaceMarineRequest createSpaceMarineRequest);

    SpaceMarineResponseDto updateSpaceMarine(Long id, UpdateSpaceMarineRequest updateSpaceMarineRequest);

    void deleteSpaceMarine(Long id);

    SpaceMarineResponseDto getSpaceMarineById(Long id);

    List<SpaceMarineResponseDto> getAllSpaceMarines(List<Sort> sort, List<Filter> filter, Integer page, Integer pageSize);
}
