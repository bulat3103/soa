package com.example.mainservice.entity;

import com.example.mainservice.model.AstartesCategory;
import com.example.mainservice.model.Chapter;
import com.example.mainservice.model.Coordinates;
import com.example.mainservice.model.response.SpaceMarineResponseDto;
import com.example.mainservice.model.response.StarShipResponseDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "spacemarine")
public class SpaceMarine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "coordinate_x")
    private Long coordinateX;

    @Column(name = "coordinate_y")
    private Double coordinateY;

    @Column(name = "creation_date")
    private Instant creationDate;

    @Column(name = "health")
    private Double health;

    @Column(name = "heart_count")
    private Integer heartCount;

    @Column(name = "achievements")
    private String achievements;

    @Column(name = "category")
    @Enumerated(EnumType.ORDINAL)
    private AstartesCategory category;

    @Column(name = "chapter_name")
    private String chapterName;

    @Column(name = "chapter_spacemarines_count")
    private Integer chapterSpaceMarinesCount;

    @Column(name = "starship_id", insertable = false, updatable = false)
    private Long starshipId;

    @ManyToOne
    @JoinColumn(name = "starship_id")
    private StarShip starShip;

    public static SpaceMarineResponseDto buildResponseDto(SpaceMarine spaceMarine) {
        return new SpaceMarineResponseDto(
                spaceMarine.getId(),
                spaceMarine.getName(),
                new Coordinates(
                        spaceMarine.getCoordinateX(),
                        spaceMarine.getCoordinateY()
                ),
                spaceMarine.getCreationDate(),
                spaceMarine.getHealth(),
                spaceMarine.getHeartCount(),
                spaceMarine.getAchievements(),
                spaceMarine.getCategory(),
                new Chapter(
                        spaceMarine.getChapterName(),
                        spaceMarine.getChapterSpaceMarinesCount()
                ),
                spaceMarine.getStarShip() == null ? null :
                        new StarShipResponseDto(
                                spaceMarine.getStarShip().getId(),
                                spaceMarine.getStarShip().getName(),
                                new Coordinates(
                                        spaceMarine.getStarShip().getCoordinateX(),
                                        spaceMarine.getStarShip().getCoordinateY()
                                ),
                                spaceMarine.getStarShip().getCrewCount(),
                                spaceMarine.getStarShip().getHealth()
                        )
        );
    }
}
