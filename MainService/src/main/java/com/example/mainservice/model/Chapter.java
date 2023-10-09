package com.example.mainservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chapter {
    @NotNull(message = "Поле chapterName не может быть null")
    @NotBlank(message = "Поле chapterName не может быть пустым")
    private String name;
    @Min(value = 1, message = "Поле spaceMarinesCount должно быть больше 0")
    @Max(value = 1000, message = "Поле spaceMarinesCount должно быть меньше 1001")
    private Integer spaceMarinesCount;
}
