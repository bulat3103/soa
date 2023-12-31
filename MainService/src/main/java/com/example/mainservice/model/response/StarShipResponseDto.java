package com.example.mainservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StarShipResponseDto {
    private Long id;
    private String name;
    private CoordinatesResponseDto coordinates;
    private Integer crewCount;
    private Integer health;
}
