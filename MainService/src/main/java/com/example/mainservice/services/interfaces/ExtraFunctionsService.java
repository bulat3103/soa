package com.example.mainservice.services.interfaces;

import com.example.mainservice.model.response.ListSpaceMarine;

import java.util.List;

public interface ExtraFunctionsService {
    List<Integer> getUniqueHeartCount();

    Integer getLowerAchieves(String achieve);

    ListSpaceMarine getByPattern(String field, String value);
}
