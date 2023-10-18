package com.example.mainservice.services.implementations;

import com.example.mainservice.entity.SpaceMarine;
import com.example.mainservice.entity.StarShip;
import com.example.mainservice.model.Filter;
import com.example.mainservice.model.Sort;
import com.example.mainservice.model.request.SpaceMarineBuildDto;
import com.example.mainservice.model.response.SpaceMarineResponseDto;
import com.example.mainservice.repositories.SpaceMarineRepository;
import com.example.mainservice.repositories.StarShipRepository;
import com.example.mainservice.services.interfaces.SpaceMarineService;
import com.example.mainservice.utils.BuildEntityFromDtos;
import com.example.mainservice.validators.SpaceMarineBuildDtoValidator;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class SpaceMarineServiceImpl implements SpaceMarineService {
    @Inject
    private SpaceMarineRepository spaceMarineRepository;
    @Inject
    private StarShipRepository shipRepository;

    @Override
    public SpaceMarineResponseDto createSpaceMarine(SpaceMarineBuildDto spaceMarineBuildDto) {
        SpaceMarineBuildDtoValidator.validate(spaceMarineBuildDto);
        SpaceMarine spaceMarine = BuildEntityFromDtos.buildSpaceMarine(spaceMarineBuildDto);
        if (spaceMarineBuildDto.getStarshipId() == null) {
            spaceMarine.setStarShip(null);
            return SpaceMarine.buildResponseDto(spaceMarineRepository.save(spaceMarine));
        }
        StarShip starShip = shipRepository.getById(Long.parseLong(spaceMarineBuildDto.getStarshipId()));
        if (starShip == null) throw new NotFoundException("Not found");
        spaceMarine.setStarShip(starShip);
        return SpaceMarine.buildResponseDto(spaceMarineRepository.save(spaceMarine));
    }

    @Override
    public SpaceMarineResponseDto updateSpaceMarine(Long id, SpaceMarineBuildDto spaceMarineBuildDto) {
        SpaceMarineBuildDtoValidator.validate(spaceMarineBuildDto);
        SpaceMarine spaceMarine = spaceMarineRepository.getById(id);
        if (spaceMarine == null) throw new NotFoundException("Not found");
        BuildEntityFromDtos.buildCommonSpaceMarineFields(spaceMarine, spaceMarineBuildDto);
        if (spaceMarineBuildDto.getStarshipId() == null) {
            spaceMarine.setStarShip(null);
            return SpaceMarine.buildResponseDto(spaceMarineRepository.save(spaceMarine));
        }
        StarShip starShip = shipRepository.getById(Long.parseLong(spaceMarineBuildDto.getStarshipId()));
        if (starShip == null) throw new NotFoundException("Not found");
        spaceMarine.setStarShip(starShip);
        return SpaceMarine.buildResponseDto(spaceMarineRepository.save(spaceMarine));
    }

    @Override
    public void deleteSpaceMarine(Long id) {
        SpaceMarine spaceMarine = spaceMarineRepository.getById(id);
        if (spaceMarine == null) throw new NotFoundException("Not found");
        spaceMarineRepository.deleteById(id);
    }

    @Override
    public SpaceMarineResponseDto getSpaceMarineById(Long id) {
        SpaceMarine spaceMarine = spaceMarineRepository.getById(id);
        if (spaceMarine == null) throw new NotFoundException("Not found");
        return SpaceMarine.buildResponseDto(spaceMarine);
    }

    @Override
    public List<SpaceMarineResponseDto> getAllSpaceMarines(List<Sort> sort, List<Filter> filter, Integer page, Integer pageSize) {
        return spaceMarineRepository
                .getAllSpaceMarines(sort, filter, page, pageSize)
                .stream()
                .map(SpaceMarine::buildResponseDto)
                .collect(Collectors.toList());
    }
}
