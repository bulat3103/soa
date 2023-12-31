package com.example.extraservice.model.response;

import com.example.extraservice.model.Coordinates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StarShipResponseDto {
    private Long id;
    private String name;
    private Coordinates coordinates;
    private Integer crewCount;
    private Integer health;
}
