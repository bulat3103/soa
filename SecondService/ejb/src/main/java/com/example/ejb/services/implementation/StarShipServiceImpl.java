package com.example.ejb.services.implementation;

import com.example.ejb.model.request.CoordinatesRequestDto;
import com.example.ejb.model.request.SpaceMarineBuildDto;
import com.example.ejb.model.request.StarShipCreateDto;
import com.example.ejb.model.response.SpaceMarineResponseDto;
import com.example.ejb.services.StarShipService;
import org.jboss.ejb3.annotation.Pool;

import javax.ejb.Stateless;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Pool(value = "starshipServicePool")
public class StarShipServiceImpl implements StarShipService {

    private static final String MAIN_SERVICE_SPACEMARINES_URL = "https://localhost:7112/api/v1/spacemarines";
    private static final String MAIN_SERVICE_STARSHIP_URL = "https://localhost:7112/api/v1/starship";

    public Integer unloadAll(Long id) {
        Response getResponse = performGetSpaceMarines(id);
        List<SpaceMarineResponseDto> listSpaceMarine = getResponse.readEntity(new GenericType<List<SpaceMarineResponseDto>>() {});
        if (listSpaceMarine == null) return 404;
        for (SpaceMarineResponseDto dto : listSpaceMarine) {
            SpaceMarineBuildDto spaceMarineBuildDto = buildFromResponse(dto);
            Response updateResponse = performUpdateSpaceMarine(dto.getId(), spaceMarineBuildDto);
            if (updateResponse.getStatus() != 200) {
                return updateResponse.getStatus();
            }
        }
        return 200;
    }

    public Integer createNewStarShip(
            Long id,
            String name,
            String coordX,
            String coordY,
            String crewCount,
            String health)
    {
        StarShipCreateDto starShipCreateDto = new StarShipCreateDto(
                String.valueOf(id),
                name,
                new CoordinatesRequestDto(coordX, coordY),
                crewCount,
                health
        );
        Response response = performCreateStarShip(starShipCreateDto);
        return response.getStatus();
    }


    private SpaceMarineBuildDto buildFromResponse(SpaceMarineResponseDto responseDto) {
        return new SpaceMarineBuildDto(
                responseDto.getName(),
                responseDto.getCoordinates(),
                responseDto.getHealth(),
                responseDto.getHeartCount(),
                responseDto.getAchievements(),
                responseDto.getCategory(),
                responseDto.getChapter(),
                null
        );
    }

    private Response performCreateStarShip(StarShipCreateDto createDto) {
        return ClientBuilder.newClient()
                .target(MAIN_SERVICE_STARSHIP_URL)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(createDto));
    }

    private Response performGetSpaceMarines(long id) {
        return ClientBuilder.newClient()
                .target(MAIN_SERVICE_SPACEMARINES_URL)
                .queryParam("filter", "starship[eq]=" + id)
                .request()
                .get();
    }

    private Response performUpdateSpaceMarine(Long id, SpaceMarineBuildDto dto) {
        return ClientBuilder.newClient()
                .target(MAIN_SERVICE_SPACEMARINES_URL)
                .path("/{id}")
                .resolveTemplate("id", id)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(dto));
    }
}
