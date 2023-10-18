package com.example.extraservice.utils;

import com.example.extraservice.model.request.SpaceMarineBuildDto;
import com.example.extraservice.model.request.StarShipCreateDto;
import com.example.extraservice.model.response.ListSpaceMarine;
import com.example.extraservice.model.response.SpaceMarineResponseDto;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Component
public class RestClient implements InitializingBean {
    private RestTemplate restTemplate;

    @Value("${main-service.url}")
    private String mainServiceUrl;

    @Override
    public void afterPropertiesSet() throws Exception {
        SSLValidation.sslCheckingOff();
        restTemplate = new RestTemplate();
    }

    public ResponseEntity<?> createStarShip(StarShipCreateDto dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = mainServiceUrl + MainServiceEndpoints.STARSHIP;
        HttpEntity<StarShipCreateDto> httpEntity = new HttpEntity<>(dto, headers);
        return restTemplate.exchange(url, HttpMethod.POST, httpEntity, Void.class);
    }

    public ResponseEntity<SpaceMarineResponseDto> updateSpacemarine(Long id, SpaceMarineBuildDto dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = mainServiceUrl + MainServiceEndpoints.SPACEMARINES + id;
        HttpEntity<SpaceMarineBuildDto> httpEntity = new HttpEntity<>(dto, headers);
        return restTemplate.exchange(url, HttpMethod.PUT, httpEntity, SpaceMarineResponseDto.class);
    }

    public ResponseEntity<ListSpaceMarine> getSpaceMarines(Long starShipId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        String url = UriComponentsBuilder.fromHttpUrl(mainServiceUrl + MainServiceEndpoints.SPACEMARINES)
                .queryParam("filter", "{filter}")
                .encode()
                .toUriString();
        Map<String, String> params = new HashMap<>();
        params.put("filter", "id[eq]=" + starShipId);
        return restTemplate.exchange(url, HttpMethod.GET, httpEntity, ListSpaceMarine.class, params);
    }
}
