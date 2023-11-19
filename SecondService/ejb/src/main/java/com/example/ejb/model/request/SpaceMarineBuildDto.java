package com.example.ejb.model.request;

import com.example.ejb.model.AstartesCategory;
import com.example.ejb.model.Chapter;
import com.example.ejb.model.Coordinates;
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
