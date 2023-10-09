package com.example.mainservice.model.request;

import com.example.mainservice.model.Coordinates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StarShipCreateDto {
    @NotNull(message = "Поле id не может быть null")
    @Min(value = 1, message = "Поле id должно быть больше 0")
    private Long id;
    @NotNull(message = "Поле name не может быть null")
    @NotBlank(message = "Поле name не может быть пустым")
    private String name;
    @NotNull(message = "Поле coordinates не может быть null")
    private Coordinates coordinates;
    @NotNull(message = "Поле crewCount не может быть null")
    @Min(value = 1, message = "Поле crewCount должно быть больше 0")
    private Integer crewCount;
    @NotNull(message = "Поле health не может быть null")
    @Min(value = 1, message = "Поле health должно быть больше 0")
    private Integer health;
}
