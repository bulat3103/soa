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
    @JoinColumn(name = "starship_id")
    private StarShip starShip;

    @Column(name = "starship_id", insertable = false, updatable = false)
    private Long starshipId;

    @Column(name = "starship_name", insertable = false, updatable = false)
    private String starshipName;

    @Column(name = "starship_coordinates_x", insertable = false, updatable = false)
    private Long starshipCoordinatesX;

    @Column(name = "starship_coordinates_y", insertable = false, updatable = false)
    private Double starshipCoordinatesY;

    @Column(name = "starship_crew_count", insertable = false, updatable = false)
    private Integer starshipCrewCount;

    @Column(name = "starship_health", insertable = false, updatable = false)
    private Integer starshipHealth;

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
                spaceMarine.getStarShip() == null ? null :
                        new StarShipResponseDto(
                                spaceMarine.getStarShip().getId(),
                                spaceMarine.getStarShip().getName(),
                                new CoordinatesResponseDto(
                                        spaceMarine.getStarShip().getCoordinateX(),
                                        spaceMarine.getStarShip().getCoordinateY()
                                ),
                                spaceMarine.getStarShip().getCrewCount(),
                                spaceMarine.getStarShip().getHealth()
                        )
        );
    }
}
