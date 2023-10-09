package com.example.mainservice.services.interfaces;

import com.example.mainservice.model.response.ListSpaceMarine;
import com.example.mainservice.model.response.UniqueHeart;

import java.util.List;

public interface ExtraFunctionsService {
    UniqueHeart getUniqueHeartCount();

    Integer getLowerAchieves(String achieve);

    ListSpaceMarine getByPattern(String field, String value);
}
