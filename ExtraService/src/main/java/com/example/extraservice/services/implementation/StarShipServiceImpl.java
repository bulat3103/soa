package com.example.extraservice.services.implementation;

import com.example.extraservice.exceptions.InvalidParamException;
import com.example.extraservice.model.request.SpaceMarineBuildDto;
import com.example.extraservice.model.request.StarShipCreateDto;
import com.example.extraservice.model.response.SpaceMarineResponseDto;
import com.example.extraservice.services.StarShipService;
import com.example.extraservice.utils.ErrorResponse;
import com.example.extraservice.utils.RestClient;
import com.example.extraservice.validators.StarShipCreateDtoValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class StarShipServiceImpl implements StarShipService {
    private final RestClient restClient;

    public StarShipServiceImpl(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public ResponseEntity<?> unloadAll(Long id) {
        try {
            ResponseEntity<List<SpaceMarineResponseDto>> responseEntity = restClient.getSpaceMarines(id);
            if (responseEntity.getBody() == null) {
                return ErrorResponse.buildResponse(404, "Not found");
            }
            List<SpaceMarineResponseDto> listSpaceMarine = responseEntity.getBody();
            for (SpaceMarineResponseDto dto : listSpaceMarine) {
                SpaceMarineBuildDto spaceMarineBuildDto = buildFromResponse(dto);
                ResponseEntity<SpaceMarineResponseDto> updated = restClient.updateSpacemarine(dto.getId(), spaceMarineBuildDto);
                if (updated.getStatusCode().value() != 200) {
                    return ErrorResponse.buildResponse(500, "Internal server error");
                }
            }
            return ResponseEntity.ok().build();
        } catch (HttpClientErrorException.NotFound e) {
            return ErrorResponse.buildResponse(404, "Not found");
        } catch (HttpClientErrorException.BadRequest e) {
            return ErrorResponse.buildResponse(400, "Validation failed");
        } catch (HttpServerErrorException.InternalServerError e) {
            return ErrorResponse.buildResponse(500, "Internal server error");
        }
    }

    @Override
    public ResponseEntity<?> createNewStarShip(Long id, String name, StarShipCreateDto createDto) {
        try {
            StarShipCreateDtoValidator.validate(createDto);
        } catch (InvalidParamException e) {
            return ErrorResponse.buildResponse(400, "Validation failed");
        }
        if (id != Long.parseLong(createDto.getId()) && !Objects.equals(name, createDto.getName())) {
            return ErrorResponse.buildResponse(400, "Validation failed");
        }
        try {
            restClient.createStarShip(createDto);
            return ResponseEntity.ok().build();
        } catch (HttpClientErrorException.BadRequest e) {
            return ErrorResponse.buildResponse(400, "Validation failed");
        } catch (HttpClientErrorException.NotFound e) {
            return ErrorResponse.buildResponse(404, "Not found");
        } catch (HttpServerErrorException.InternalServerError e) {
            return ErrorResponse.buildResponse(500, "Internal server error");
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
