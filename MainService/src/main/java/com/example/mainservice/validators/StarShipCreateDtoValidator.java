package com.example.mainservice.validators;

import com.example.mainservice.exceptions.InvalidParamException;
import com.example.mainservice.model.request.StarShipCreateDto;

public class StarShipCreateDtoValidator {

    public static void validate(StarShipCreateDto dto) {
        if (dto.getId() == null) throw new InvalidParamException("Validation failed");
        try {
            long l = Long.parseLong(dto.getId());
            if (l <= 0) throw new InvalidParamException("Validation failed");
        } catch (NumberFormatException e) {
            throw new InvalidParamException("Validation failed");
        }
        if (dto.getName() == null || dto.getName().isEmpty() || dto.getName().length() > 255) throw new InvalidParamException("Validation failed");
        if (dto.getCoordinates() == null) throw new InvalidParamException("Validation failed");
        else {
            if (dto.getCoordinates().getX() == null) throw new InvalidParamException("Validation failed");
            try {
                long i = Long.parseLong(dto.getCoordinates().getX());
                if (i < 0 || i > 3) throw new InvalidParamException("Validation failed");
            } catch (NumberFormatException e) {
                throw new InvalidParamException("Validation failed");
            }
            if (dto.getCoordinates().getY() == null) throw new InvalidParamException("Validation failed");
            try {
                double d = Double.parseDouble(dto.getCoordinates().getY());
                if (d > 4) throw new InvalidParamException("Validation failed");
            } catch (NumberFormatException e) {
                throw new InvalidParamException("Validation failed");
            }
            if (dto.getCrewCount() == null) throw new InvalidParamException("Validation failed");
            try {
                int i = Integer.parseInt(dto.getCrewCount());
                if (i <= 0) throw new InvalidParamException("Validation failed");
            } catch (NumberFormatException e) {
                throw new InvalidParamException("Validation failed");
            }
            if (dto.getHealth() == null) throw new InvalidParamException("Validation failed");
            try {
                int i = Integer.parseInt(dto.getHealth());
                if (i <= 0) throw new InvalidParamException("Validation failed");
            } catch (NumberFormatException e) {
                throw new InvalidParamException("Validation failed");
            }
        }
    }
}
