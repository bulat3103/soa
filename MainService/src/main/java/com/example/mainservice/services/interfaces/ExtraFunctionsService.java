package com.example.mainservice.services.interfaces;

import com.example.mainservice.catalog.GetUniqueHeartResponse;
import com.example.mainservice.catalog.SpaceMarineResponseDto;

import java.util.List;

public interface ExtraFunctionsService {
    GetUniqueHeartResponse getUniqueHeartCount();

    Integer getLowerAchieves(String achieve);

    List<SpaceMarineResponseDto> getByPattern(String field, String value);
}
