package com.example.mainservice.services.implementations;

import com.example.mainservice.entity.StarShip;
import com.example.mainservice.exceptions.InvalidParamException;
import com.example.mainservice.model.request.StarShipCreateDto;
import com.example.mainservice.repositories.StarShipRepository;
import com.example.mainservice.services.interfaces.StarShipService;
import com.example.mainservice.validators.StarShipCreateDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StarShipServiceImpl implements StarShipService {
    private final StarShipRepository shipRepository;

    @Autowired
    public StarShipServiceImpl(StarShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    @Override
    public void createStarShip(StarShipCreateDto starShipCreateDto) {
        StarShipCreateDtoValidator.validate(starShipCreateDto);
        StarShip inDb = shipRepository.getById(Long.parseLong(starShipCreateDto.getId()));
        if (inDb != null) throw new InvalidParamException("Validation failed");
        StarShip starShip = new StarShip();
        starShip.setId(Long.parseLong(starShipCreateDto.getId()));
        starShip.setName(starShipCreateDto.getName());
        starShip.setCoordinateX(Long.parseLong(starShipCreateDto.getCoordinates().getX()));
        starShip.setCoordinateY(Double.parseDouble(starShipCreateDto.getCoordinates().getY()));
        starShip.setCrewCount(Integer.parseInt(starShipCreateDto.getCrewCount()));
        starShip.setHealth(Integer.parseInt(starShipCreateDto.getHealth()));
        shipRepository.save(starShip);
    }
}
