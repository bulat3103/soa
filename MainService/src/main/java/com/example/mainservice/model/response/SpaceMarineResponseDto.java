package com.example.mainservice.model.response;

import com.example.mainservice.model.AstartesCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpaceMarineResponseDto {
    private Long id;
    private String name;
    private CoordinatesResponseDto coordinates;
    private String creationDate;
    private Double health;
    private Integer heartCount;
    private String achievements;
    private AstartesCategory category;
    private ChapterResponseDto chapter;
    private StarShipResponseDto starShip;
}
