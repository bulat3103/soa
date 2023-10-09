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
        SpaceMarine spaceMarine = BuildEntityFromDtos.buildSpaceMarine(spaceMarineBuildDto);
        if (spaceMarineBuildDto.getStarshipId() == null) {
            spaceMarine.setStarShip(null);
            return SpaceMarine.buildResponseDto(spaceMarineRepository.save(spaceMarine));
        }
        StarShip starShip = shipRepository.getById(spaceMarineBuildDto.getStarshipId());
        if (starShip == null) throw new NotFoundException("Starship with id = " + spaceMarineBuildDto.getStarshipId() + " doesn't exist");
        spaceMarine.setStarShip(starShip);
        return SpaceMarine.buildResponseDto(spaceMarineRepository.save(spaceMarine));
    }

    @Override
    public SpaceMarineResponseDto updateSpaceMarine(Long id, SpaceMarineBuildDto spaceMarineBuildDto) {
        SpaceMarine spaceMarine = spaceMarineRepository.getById(id);
        if (spaceMarine == null) throw new NotFoundException("SpaceMarine with id = " + id + " doesn't exist");
        BuildEntityFromDtos.buildCommonSpaceMarineFields(spaceMarine, spaceMarineBuildDto);
        if (spaceMarineBuildDto.getStarshipId() == null) {
            spaceMarine.setStarShip(null);
            return SpaceMarine.buildResponseDto(spaceMarineRepository.save(spaceMarine));
        }
        StarShip starShip = shipRepository.getById(spaceMarineBuildDto.getStarshipId());
        if (starShip == null) throw new NotFoundException("Starship with id = " + spaceMarineBuildDto.getStarshipId() + " doesn't exist");
        spaceMarine.setStarShip(starShip);
        return SpaceMarine.buildResponseDto(spaceMarineRepository.save(spaceMarine));
    }

    @Override
    public void deleteSpaceMarine(Long id) {
        SpaceMarine spaceMarine = spaceMarineRepository.getById(id);
        if (spaceMarine == null) throw new NotFoundException("SpaceMarine with id = " + id + " doesn't exist");
        spaceMarineRepository.deleteById(id);
    }

    @Override
    public SpaceMarineResponseDto getSpaceMarineById(Long id) {
        SpaceMarine spaceMarine = spaceMarineRepository.getById(id);
        if (spaceMarine == null) throw new NotFoundException("SpaceMarine with id = " + id + " doesn't exist");
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
