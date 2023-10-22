package com.example.extraservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StarShipCreateDto {
    private String id;
    private String name;
    private CoordinatesRequestDto coordinates;
    private String crewCount;
    private String health;
}
