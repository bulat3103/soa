package com.example.mainservice.services.implementations;

import com.example.mainservice.catalog.GetUniqueHeartResponse;
import com.example.mainservice.catalog.SpaceMarineResponseDto;
import com.example.mainservice.entity.SpaceMarine;
import com.example.mainservice.repositories.SpaceMarineRepository;
import com.example.mainservice.services.interfaces.ExtraFunctionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExtraFunctionsServiceImpl implements ExtraFunctionsService {
    private final SpaceMarineRepository spaceMarineRepository;

    @Autowired
    public ExtraFunctionsServiceImpl(SpaceMarineRepository spaceMarineRepository) {
        this.spaceMarineRepository = spaceMarineRepository;
    }

    @Override
    public GetUniqueHeartResponse getUniqueHeartCount() {
        GetUniqueHeartResponse response = new GetUniqueHeartResponse();
        response.getHearts().addAll(spaceMarineRepository.getUniqueHeartCountColumn());
        return response;
    }

    @Override
    public Integer getLowerAchieves(String achieve) {
        return spaceMarineRepository.getLowerAchieves(achieve);
    }

    @Override
    public List<SpaceMarineResponseDto> getByPattern(String field, String value) {
        List<SpaceMarine> byPattern = spaceMarineRepository.getByPattern(field, value);
        return byPattern.stream().map(SpaceMarine::buildResponseDto).collect(Collectors.toList());
    }
}
