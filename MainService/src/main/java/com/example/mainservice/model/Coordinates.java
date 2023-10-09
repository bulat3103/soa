package com.example.mainservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coordinates {
    @NotNull(message = "Координата X не может быть null")
    private Long x;
    @NotNull(message = "Координата Y не может быть null")
    @Max(value = 4, message = "Координата Y должна быть не больше 4")
    private Double y;
}
