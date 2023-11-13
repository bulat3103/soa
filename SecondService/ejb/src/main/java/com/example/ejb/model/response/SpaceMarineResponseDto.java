package com.example.ejb.model.response;

import com.example.ejb.model.AstartesCategory;
import com.example.ejb.model.Chapter;
import com.example.ejb.model.Coordinates;
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
