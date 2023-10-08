package com.example.extraservice.model.request;

import com.example.extraservice.model.AstartesCategory;
import com.example.extraservice.model.Chapter;
import com.example.extraservice.model.Coordinates;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpaceMarineBuildDto {
    private String name;
    private Coordinates coordinates;
    private Double health;
    private Integer heartCount;
    private String achievements;
    private AstartesCategory category;
    private Chapter chapter;
    private Long starshipId;
}
