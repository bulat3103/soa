package com.example.mainservice.services.implementations;

import com.example.mainservice.entity.SpaceMarine;
import com.example.mainservice.model.response.ListSpaceMarine;
import com.example.mainservice.model.response.SpaceMarineResponseDto;
import com.example.mainservice.repositories.SpaceMarineRepository;
import com.example.mainservice.services.interfaces.ExtraFunctionsService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ExtraFunctionsServiceImpl implements ExtraFunctionsService {
    @Inject
    private SpaceMarineRepository spaceMarineRepository;

    @Override
    public List<Integer> getUniqueHeartCount() {
        return spaceMarineRepository.getUniqueHeartCountColumn();
    }

    @Override
    public Integer getLowerAchieves(String achieve) {
        return spaceMarineRepository.getLowerAchieves(achieve);
    }

    @Override
    public ListSpaceMarine getByPattern(String field, String value) {
        List<SpaceMarine> byPattern = spaceMarineRepository.getByPattern(field, value);
        List<SpaceMarineResponseDto> convertToDto = byPattern.stream().map(SpaceMarine::buildResponseDto).collect(Collectors.toList());
        return new ListSpaceMarine(convertToDto);
    }
}
