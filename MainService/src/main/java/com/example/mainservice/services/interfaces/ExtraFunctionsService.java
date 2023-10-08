package com.example.mainservice.services.interfaces;

import model.response.ListSpaceMarine;

import java.util.List;

public interface ExtraFunctionsService {
    List<Integer> getUniqueHeartCount();

    Integer getLowerAchieves(String achieve);

    ListSpaceMarine getByPattern(String field, String value);
}
