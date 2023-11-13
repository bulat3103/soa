package com.example.api.controllers;

import com.example.api.exceptions.InternalServerError;
import com.example.api.exceptions.InvalidParamException;
import com.example.api.exceptions.NotFoundException;
import com.example.api.model.StarShipCreateDto;
import com.example.api.validators.StarShipCreateDtoValidator;
import com.example.ejb.services.StarShipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("starship")
public class StartShipController {
    private final StarShipService starShipService;

    public StartShipController(StarShipService starShipService) {
        this.starShipService = starShipService;
    }

    @CrossOrigin
    @GetMapping(value = "/hello")
    public String hello() {
        return "Hello, World!";
    }

    @CrossOrigin
    @PostMapping(value = "/create/{id}/{name}")
    public ResponseEntity<?> createNewStarShip(
            @PathVariable("id") String idStr,
            @PathVariable("name") String name,
            @RequestBody StarShipCreateDto starShipCreateDto)
    {
        long id = Long.parseLong(idStr);
        StarShipCreateDtoValidator.validate(starShipCreateDto);
        Integer status = starShipService.createNewStarShip(
                id,
                name,
                starShipCreateDto.getCoordinates().getX(),
                starShipCreateDto.getCoordinates().getY(),
                starShipCreateDto.getCrewCount(),
                starShipCreateDto.getHealth());
        if (status == 400) {
            throw new InvalidParamException("Validation failed");
        } else if (status == 404) {
            throw new NotFoundException("Not found");
        } else if (status == 500) {
            throw new InternalServerError("Internal server error");
        }
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @PostMapping(value = "/{id}/unload-all")
    public ResponseEntity<?> unloadAll(@PathVariable("id") String idStr) {
        long id = Long.parseLong(idStr);
        Integer status = starShipService.unloadAll(id);
        if (status == 400) {
            throw new InvalidParamException("Validation failed");
        } else if (status == 404) {
            throw new NotFoundException("Not found");
        } else if (status == 500) {
            throw new InternalServerError("Internal server error");
        }
        return ResponseEntity.ok().build();
    }
}
