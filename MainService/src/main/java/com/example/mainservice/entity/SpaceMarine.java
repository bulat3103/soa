package com.example.mainservice.entity;


import com.example.mainservice.catalog.ChapterResponseDto;
import com.example.mainservice.catalog.CoordinatesResponseDto;
import com.example.mainservice.catalog.SpaceMarineResponseDto;
import com.example.mainservice.catalog.StarShipResponseDto;
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
    private String category;

    @Column(name = "chapter_name")
    private String chapterName;

    @Column(name = "chapter_marines_count")
    private Integer chapterMarinesCount;

    @ManyToOne
    @JoinColumn(name = "starship")
    private StarShip starship;

    public static SpaceMarineResponseDto buildResponseDto(SpaceMarine spaceMarine) {
        SpaceMarineResponseDto spaceMarineDto = new SpaceMarineResponseDto();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        ChapterResponseDto chapter = new ChapterResponseDto();
        chapter.setName(spaceMarine.getChapterName());
        chapter.setMarinesCount(spaceMarine.getChapterMarinesCount());

        CoordinatesResponseDto coordinatesSpaceMarine = new CoordinatesResponseDto();
        coordinatesSpaceMarine.setX(spaceMarine.getCoordinateX());
        coordinatesSpaceMarine.setY(spaceMarine.getCoordinateY());

        CoordinatesResponseDto coordinatesStarShip = new CoordinatesResponseDto();
        coordinatesSpaceMarine.setX(spaceMarine.getStarship().getCoordinateX());
        coordinatesSpaceMarine.setY(spaceMarine.getStarship().getCoordinateY());

        if (spaceMarine.getStarship() != null) {
            StarShipResponseDto starShip = new StarShipResponseDto();
            starShip.setId(spaceMarine.getStarship().getId());
            starShip.setName(spaceMarine.getStarship().getName());
            starShip.setCoordinates(coordinatesStarShip);
            starShip.setHealth(spaceMarine.getStarship().getHealth());
            starShip.setCrewCount(spaceMarine.getStarship().getHealth());
            spaceMarineDto.setStarship(starShip);
        }
        spaceMarineDto.setId(spaceMarine.getId());
        spaceMarineDto.setName(spaceMarine.getName());
        spaceMarineDto.setCoordinates(coordinatesSpaceMarine);
        spaceMarineDto.setCreationDate(spaceMarine.getCreationDate().format(formatter));
        spaceMarineDto.setHealth(spaceMarine.getHealth());
        spaceMarineDto.setHeartCount(spaceMarine.getHeartCount());
        spaceMarineDto.setAchievements(spaceMarine.getAchievements());
        spaceMarineDto.setCategory(spaceMarine.getCategory());
        spaceMarineDto.setChapter(chapter);
        return spaceMarineDto;
    }
}
