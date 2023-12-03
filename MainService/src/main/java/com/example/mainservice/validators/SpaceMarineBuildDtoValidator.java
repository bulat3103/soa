package com.example.mainservice.validators;

import com.example.mainservice.catalog.CreateSpaceMarineRequest;
import com.example.mainservice.exceptions.InvalidParamException;
import com.example.mainservice.model.AstartesCategory;

public class SpaceMarineBuildDtoValidator {

    public static void validate(CreateSpaceMarineRequest dto) {
        if (dto.getName() == null || dto.getName().isEmpty() || dto.getName().length() > 255) throw new InvalidParamException("Validation failed");
        if (dto.getAchievements() != null && dto.getAchievements().length() > 255) throw new InvalidParamException("Validation failed");
        if (dto.getCategory() == null) throw new InvalidParamException("Validation failed");
        try {
            AstartesCategory.valueOf(dto.getCategory().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidParamException("Validation failed");
        }
        if (dto.getHealth() == null) throw new InvalidParamException("Validation failed");
        try {
            double d = Double.parseDouble(dto.getHealth());
            if (d < 0) throw new InvalidParamException("Validation failed");
        } catch (NumberFormatException e) {
            throw new InvalidParamException("Validation failed");
        }
        if (dto.getHeartCount() == null) throw new InvalidParamException("Validation failed");
        try {
            int i = Integer.parseInt(dto.getHeartCount());
            if (i < 0 || i > 3) throw new InvalidParamException("Validation failed");
        } catch (NumberFormatException e) {
            throw new InvalidParamException("Validation failed");
        }
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
        }
        if (dto.getChapter() == null) throw new InvalidParamException("Validation failed");
        else {
            if (dto.getChapter().getName() == null) throw new InvalidParamException("Validation failed");
            if (dto.getChapter().getMarinesCount() == null) throw new InvalidParamException("Validation failed");
            try {
                int i = Integer.parseInt(dto.getChapter().getMarinesCount());
                if (i < 0 || i > 1000) throw new InvalidParamException("Validation failed");
            } catch (NumberFormatException e) {
                throw new InvalidParamException("Validation failed");
            }
        }
        if (dto.getStarship() != null) {
            try {
                Long.parseLong(dto.getStarship());
            } catch (NumberFormatException e) {
                throw new InvalidParamException("Validation failed");
            }
        }
    }
}
