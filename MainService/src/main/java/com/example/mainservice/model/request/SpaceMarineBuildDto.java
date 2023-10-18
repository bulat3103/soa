package com.example.mainservice.model.request;

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
    private CoordinatesRequestDto coordinates;
    private String health;
    private String heartCount;
    private String achievements;
    private String category;
    private ChapterRequestDto chapter;
    private String starshipId;
}
