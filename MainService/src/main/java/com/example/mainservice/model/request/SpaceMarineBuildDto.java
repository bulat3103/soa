package com.example.mainservice.model.request;

import com.example.mainservice.model.AstartesCategory;
import com.example.mainservice.model.Chapter;
import com.example.mainservice.model.Coordinates;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpaceMarineBuildDto {
    @NotNull(message = "Поле name не может быть null")
    @NotBlank(message = "Поле name не может быть пустым")
    private String name;
    @NotNull(message = "Поле coordinates не может быть null")
    private Coordinates coordinates;
    @NotNull(message = "Поле health не может быть null")
    @Min(value = 0, message = "Поле health должно быть не меньше 0")
    private Double health;
    @NotNull(message = "Поле heartCount не может быть null")
    @Min(value = 1, message = "Поле heartCount должно быть больше 0")
    @Max(value = 3, message = "Поле heartCount должно быть меньше 4")
    private Integer heartCount;
    private String achievements;
    @NotNull(message = "Поле category не может быть null")
    private AstartesCategory category;
    @NotNull(message = "Поле chapter не может быть null")
    private Chapter chapter;
    private Long starshipId;
}
