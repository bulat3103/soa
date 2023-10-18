package com.example.mainservice.entity;

import com.example.mainservice.model.AstartesCategory;
import com.example.mainservice.model.response.ChapterResponseDto;
import com.example.mainservice.model.response.CoordinatesResponseDto;
import com.example.mainservice.model.response.SpaceMarineResponseDto;
import com.example.mainservice.model.response.StarShipResponseDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

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
    private ZonedDateTime creationDate;

    @Column(name = "health")
    private Double health;

    @Column(name = "heart_count")
    private Integer heartCount;

    @Column(name = "achievements")
    private String achievements;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private AstartesCategory category;

    @Column(name = "chapter_name")
    private String chapterName;

    @Column(name = "chapter_marines_count")
    private Integer chapterMarinesCount;

    @ManyToOne
    @JoinColumn(name = "starship")
    private StarShip starship;

    public static SpaceMarineResponseDto buildResponseDto(SpaceMarine spaceMarine) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        return new SpaceMarineResponseDto(
                spaceMarine.getId(),
                spaceMarine.getName(),
                new CoordinatesResponseDto(
                        spaceMarine.getCoordinateX(),
                        spaceMarine.getCoordinateY()
                ),
                spaceMarine.getCreationDate().format(formatter),
                spaceMarine.getHealth(),
                spaceMarine.getHeartCount(),
                spaceMarine.getAchievements(),
                spaceMarine.getCategory(),
                new ChapterResponseDto(
                        spaceMarine.getChapterName(),
                        spaceMarine.getChapterMarinesCount()
                ),
                spaceMarine.getStarship() == null ? null :
                        new StarShipResponseDto(
                                spaceMarine.getStarship().getId(),
                                spaceMarine.getStarship().getName(),
                                new CoordinatesResponseDto(
                                        spaceMarine.getStarship().getCoordinateX(),
                                        spaceMarine.getStarship().getCoordinateY()
                                ),
                                spaceMarine.getStarship().getCrewCount(),
                                spaceMarine.getStarship().getHealth()
                        )
        );
    }
}
