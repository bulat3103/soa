package com.example.mainservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "starship")
public class StarShip {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "coordinate_x")
    private Long coordinateX;

    @Column(name = "coordinate_y")
    private Double coordinateY;

    @Column(name = "crew_count")
    private Integer crewCount;

    @Column(name = "health")
    private Integer health;

    @OneToMany(mappedBy = "starShip", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Set<SpaceMarine> spaceMarines;
}
