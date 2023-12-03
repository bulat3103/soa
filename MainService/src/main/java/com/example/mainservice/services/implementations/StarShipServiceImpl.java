package com.example.mainservice.services.implementations;

import com.example.mainservice.catalog.CreateStarShipRequest;
import com.example.mainservice.entity.StarShip;
import com.example.mainservice.exceptions.InvalidParamException;
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
    public void createStarShip(CreateStarShipRequest createStarShipRequest) {
        StarShipCreateDtoValidator.validate(createStarShipRequest);
        StarShip inDb = shipRepository.getById(Long.parseLong(createStarShipRequest.getId()));
        if (inDb != null) throw new InvalidParamException("Validation failed");
        StarShip starShip = new StarShip();
        starShip.setId(Long.parseLong(createStarShipRequest.getId()));
        starShip.setName(createStarShipRequest.getName());
        starShip.setCoordinateX(Long.parseLong(createStarShipRequest.getCoordinates().getX()));
        starShip.setCoordinateY(Double.parseDouble(createStarShipRequest.getCoordinates().getY()));
        starShip.setCrewCount(Integer.parseInt(createStarShipRequest.getCrewCount()));
        starShip.setHealth(Integer.parseInt(createStarShipRequest.getHealth()));
        shipRepository.save(starShip);
    }
}
