package com.example.extraservice.services.implementation;

import com.example.extraservice.services.StarShipService;
import com.example.extraservice.utils.RestClient;
import model.request.SpaceMarineBuildDto;
import model.request.StarShipCreateDto;
import model.response.ListSpaceMarine;
import model.response.SpaceMarineResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StarShipServiceImpl implements StarShipService {
    private final RestClient restClient;

    public StarShipServiceImpl(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public void unloadAll(Long id) {
        ResponseEntity<ListSpaceMarine> responseEntity = restClient.getSpaceMarines(id);
        if (responseEntity.getStatusCode().value() == 200) {
            ListSpaceMarine listSpaceMarine = responseEntity.getBody();
            if (listSpaceMarine != null && listSpaceMarine.getSpaceMarines() != null) {
                for (SpaceMarineResponseDto dto : listSpaceMarine.getSpaceMarines()) {
                    SpaceMarineBuildDto spaceMarineBuildDto = buildFromResponse(dto);
                    restClient.updateSpacemarine(dto.getId(), spaceMarineBuildDto);
                }
            }
        }
    }

    @Override
    public void createNewStarShip(Long id, String name, StarShipCreateDto createDto) {
        ResponseEntity<?> starShip = restClient.createStarShip(createDto);
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
                -1L
        );
    }
}
