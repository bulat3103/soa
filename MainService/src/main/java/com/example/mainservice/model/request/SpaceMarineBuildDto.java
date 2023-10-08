package com.example.mainservice.model.request;

import com.example.mainservice.model.AstartesCategory;
import com.example.mainservice.model.Chapter;
import com.example.mainservice.model.Coordinates;
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
