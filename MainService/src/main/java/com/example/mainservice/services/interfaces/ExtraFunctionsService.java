package com.example.mainservice.services.interfaces;

import com.example.mainservice.model.response.ListSpaceMarine;
import com.example.mainservice.model.response.SpaceMarineResponseDto;
import com.example.mainservice.model.response.UniqueHeart;

import java.util.List;

public interface ExtraFunctionsService {
    UniqueHeart getUniqueHeartCount();

    Integer getLowerAchieves(String achieve);

    List<SpaceMarineResponseDto> getByPattern(String field, String value);
}
