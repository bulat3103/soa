package com.example.extraservice.services.implementation;

import com.example.extraservice.model.Error;
import com.example.extraservice.model.request.SpaceMarineBuildDto;
import com.example.extraservice.model.request.StarShipCreateDto;
import com.example.extraservice.model.response.ListSpaceMarine;
import com.example.extraservice.model.response.SpaceMarineResponseDto;
import com.example.extraservice.services.StarShipService;
import com.example.extraservice.utils.RestClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class StarShipServiceImpl implements StarShipService {
    private final RestClient restClient;

    public StarShipServiceImpl(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public ResponseEntity<?> unloadAll(Long id) {
        try {
            ResponseEntity<ListSpaceMarine> responseEntity = restClient.getSpaceMarines(id);
            ListSpaceMarine listSpaceMarine = responseEntity.getBody();
            List<SpaceMarineResponseDto> marinesUpdated = new ArrayList<>();
            for (SpaceMarineResponseDto dto : listSpaceMarine.getSpaceMarines()) {
                SpaceMarineBuildDto spaceMarineBuildDto = buildFromResponse(dto);
                ResponseEntity<SpaceMarineResponseDto> updated = restClient.updateSpacemarine(dto.getId(), spaceMarineBuildDto);
                marinesUpdated.add(updated.getBody());
            }
            return ResponseEntity.ok(new ListSpaceMarine(marinesUpdated));
        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.status(404).body(new Error(400, "Not found", Timestamp.from(Instant.now())));
        } catch (HttpClientErrorException.BadRequest e) {
            return ResponseEntity.status(400).body(new Error(400, "Validation failed", Timestamp.from(Instant.now())));
        }
    }

    @Override
    public ResponseEntity<?> createNewStarShip(Long id, String name, StarShipCreateDto createDto) {
        try {
            restClient.createStarShip(createDto);
            return ResponseEntity.ok().build();
        } catch (HttpClientErrorException.BadRequest e) {
            return ResponseEntity.status(400).body(new Error(400, "Validation failed", Timestamp.from(Instant.now())));
        }
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
}
