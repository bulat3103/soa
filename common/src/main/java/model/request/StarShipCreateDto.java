package model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.Coordinates;

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
