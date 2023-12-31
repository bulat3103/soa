package com.example.mainservice.services.implementations;

import com.example.mainservice.entity.SpaceMarine;
import com.example.mainservice.entity.StarShip;
import com.example.mainservice.exceptions.NotFoundException;
import com.example.mainservice.model.Filter;
import com.example.mainservice.model.Sort;
import com.example.mainservice.model.request.SpaceMarineBuildDto;
import com.example.mainservice.model.response.SpaceMarineResponseDto;
import com.example.mainservice.repositories.SpaceMarineRepository;
import com.example.mainservice.repositories.StarShipRepository;
import com.example.mainservice.services.interfaces.SpaceMarineService;
import com.example.mainservice.utils.BuildEntityFromDtos;
import com.example.mainservice.validators.SpaceMarineBuildDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpaceMarineServiceImpl implements SpaceMarineService {
    private final SpaceMarineRepository spaceMarineRepository;
    private final StarShipRepository shipRepository;

    @Autowired
    public SpaceMarineServiceImpl(SpaceMarineRepository spaceMarineRepository, StarShipRepository shipRepository) {
        this.spaceMarineRepository = spaceMarineRepository;
        this.shipRepository = shipRepository;
    }

    @Override
    public SpaceMarineResponseDto createSpaceMarine(SpaceMarineBuildDto spaceMarineBuildDto) {
        SpaceMarineBuildDtoValidator.validate(spaceMarineBuildDto);
        SpaceMarine spaceMarine = BuildEntityFromDtos.buildSpaceMarine(spaceMarineBuildDto);
        if (spaceMarineBuildDto.getStarshipId() == null) {
            spaceMarine.setStarship(null);
            return SpaceMarine.buildResponseDto(spaceMarineRepository.save(spaceMarine));
        }
        StarShip starShip = shipRepository.getById(Long.parseLong(spaceMarineBuildDto.getStarshipId()));
        if (starShip == null) throw new NotFoundException("Not found");
        spaceMarine.setStarship(starShip);
        return SpaceMarine.buildResponseDto(spaceMarineRepository.save(spaceMarine));
    }

    @Override
    public SpaceMarineResponseDto updateSpaceMarine(Long id, SpaceMarineBuildDto spaceMarineBuildDto) {
        SpaceMarineBuildDtoValidator.validate(spaceMarineBuildDto);
        SpaceMarine spaceMarine = spaceMarineRepository.getById(id);
        if (spaceMarine == null) throw new NotFoundException("Not found");
        BuildEntityFromDtos.buildCommonSpaceMarineFields(spaceMarine, spaceMarineBuildDto);
        if (spaceMarineBuildDto.getStarshipId() == null) {
            spaceMarine.setStarship(null);
            return SpaceMarine.buildResponseDto(spaceMarineRepository.save(spaceMarine));
        }
        StarShip starShip = shipRepository.getById(Long.parseLong(spaceMarineBuildDto.getStarshipId()));
        if (starShip == null) throw new NotFoundException("Not found");
        spaceMarine.setStarship(starShip);
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
