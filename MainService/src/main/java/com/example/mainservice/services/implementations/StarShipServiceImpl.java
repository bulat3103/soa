package com.example.mainservice.services.implementations;

import com.example.mainservice.entity.SpaceMarine;
import com.example.mainservice.entity.StarShip;
import com.example.mainservice.exceptions.InvalidParamException;
import com.example.mainservice.exceptions.NotFoundException;
import com.example.mainservice.model.request.StarShipCreateDto;
import com.example.mainservice.repositories.SpaceMarineRepository;
import com.example.mainservice.repositories.StarShipRepository;
import com.example.mainservice.services.interfaces.StarShipService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class StarShipServiceImpl implements StarShipService {
    @Inject
    private StarShipRepository shipRepository;
    @Inject
    private SpaceMarineRepository spaceMarineRepository;

    @Override
    public void createStarShip(StarShipCreateDto starShipCreateDto) {
        StarShip inDb = shipRepository.getById(starShipCreateDto.getId());
        if (inDb != null) throw new InvalidParamException("StarShip with id = " + starShipCreateDto.getId() + " is already exists");
        StarShip starShip = new StarShip();
        starShip.setId(starShipCreateDto.getId());
        starShip.setName(starShipCreateDto.getName());
        starShip.setCoordinateX(starShipCreateDto.getCoordinates().getX());
        starShip.setCoordinateY(starShipCreateDto.getCoordinates().getY());
        starShip.setCrewCount(starShipCreateDto.getCrewCount());
        starShip.setCrewCount(starShipCreateDto.getCrewCount());
        shipRepository.save(starShip);
    }

    @Override
    public void unloadAllFromStarShip(Long id) {
        StarShip ship = shipRepository.getById(id);
        if (ship == null) throw new NotFoundException("StarShip with id = " + id + " doesn't exist");
        List<SpaceMarine> byStarShipId = spaceMarineRepository.getByStarShipId(id);
        for (SpaceMarine spaceMarine : byStarShipId) {
            spaceMarine.setStarShip(null);
            spaceMarineRepository.save(spaceMarine);
        }
    }
}
