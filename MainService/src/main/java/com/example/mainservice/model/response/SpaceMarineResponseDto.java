package com.example.mainservice.model.response;

import com.example.mainservice.model.AstartesCategory;
import com.example.mainservice.model.Chapter;
import com.example.mainservice.model.Coordinates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpaceMarineResponseDto {
    private Long id;
    private String name;
    private Coordinates coordinates;
    private Instant creationDate;
    private Double health;
    private Integer heartCount;
    private String achievements;
    private AstartesCategory category;
    private Chapter chapter;
    private StarShipResponseDto starShip;
}
