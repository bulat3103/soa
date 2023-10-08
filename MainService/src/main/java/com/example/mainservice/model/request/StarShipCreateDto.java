package com.example.mainservice.model.request;

import com.example.mainservice.model.Coordinates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StarShipCreateDto {
    private Long id;
    private String name;
    private Coordinates coordinates;
    private Integer crewCount;
    private Integer health;
}
