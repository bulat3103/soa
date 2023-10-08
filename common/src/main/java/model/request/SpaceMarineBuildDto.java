package model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.AstartesCategory;
import model.Chapter;
import model.Coordinates;

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
