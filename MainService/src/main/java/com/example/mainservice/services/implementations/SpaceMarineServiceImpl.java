package com.example.mainservice.services.implementations;

import com.example.mainservice.catalog.CreateSpaceMarineRequest;
import com.example.mainservice.catalog.SpaceMarineResponseDto;
import com.example.mainservice.catalog.UpdateSpaceMarineRequest;
import com.example.mainservice.entity.SpaceMarine;
import com.example.mainservice.entity.StarShip;
import com.example.mainservice.exceptions.NotFoundException;
import com.example.mainservice.model.Filter;
import com.example.mainservice.model.Sort;
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
    public SpaceMarineResponseDto createSpaceMarine(CreateSpaceMarineRequest createSpaceMarineRequest) {
        SpaceMarineBuildDtoValidator.validate(createSpaceMarineRequest);
        SpaceMarine spaceMarine = BuildEntityFromDtos.buildSpaceMarine(createSpaceMarineRequest);
        if (createSpaceMarineRequest.getStarshipId() == null) {
            spaceMarine.setStarship(null);
            return SpaceMarine.buildResponseDto(spaceMarineRepository.save(spaceMarine));
        }
        StarShip starShip = shipRepository.getById(Long.parseLong(createSpaceMarineRequest.getStarshipId()));
        if (starShip == null) throw new NotFoundException("Not found");
        spaceMarine.setStarship(starShip);
        return SpaceMarine.buildResponseDto(spaceMarineRepository.save(spaceMarine));
    }

    @Override
    public SpaceMarineResponseDto updateSpaceMarine(Long id, UpdateSpaceMarineRequest updateSpaceMarineRequest) {
        CreateSpaceMarineRequest createSpaceMarineRequest = fromUpdateRequest(updateSpaceMarineRequest);
        SpaceMarineBuildDtoValidator.validate(createSpaceMarineRequest);
        SpaceMarine spaceMarine = spaceMarineRepository.getById(id);
        if (spaceMarine == null) throw new NotFoundException("Not found");
        BuildEntityFromDtos.buildCommonSpaceMarineFields(spaceMarine, createSpaceMarineRequest);
        if (createSpaceMarineRequest.getStarshipId() == null) {
            spaceMarine.setStarship(null);
            return SpaceMarine.buildResponseDto(spaceMarineRepository.save(spaceMarine));
        }
        StarShip starShip = shipRepository.getById(Long.parseLong(createSpaceMarineRequest.getStarshipId()));
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

    private CreateSpaceMarineRequest fromUpdateRequest(UpdateSpaceMarineRequest request) {
        CreateSpaceMarineRequest create = new CreateSpaceMarineRequest();
        create.setName(request.getName());
        create.setHealth(request.getHealth());
        create.setAchievements(request.getAchievements());
        create.setHeartCount(request.getHeartCount());
        create.setCategory(request.getCategory());
        create.setCoordinates(request.getCoordinates());
        create.setChapter(request.getChapter());
        create.setStarshipId(request.getStarshipId());
        return create;
    }
}
