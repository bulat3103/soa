package com.example.mainservice.controllers;

import com.example.mainservice.entity.AvailablePatternFields;
import com.example.mainservice.exceptions.InvalidParamException;
import com.example.mainservice.model.response.UniqueHeart;
import com.example.mainservice.exceptions.NotFoundException;
import com.example.mainservice.services.interfaces.ExtraFunctionsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/spacemarines")
public class ExtraFunctionsController {
    private final ExtraFunctionsService extraFunctionsService;

    @Autowired
    public ExtraFunctionsController(ExtraFunctionsService extraFunctionsService) {
        this.extraFunctionsService = extraFunctionsService;
    }

    @GetMapping("/lower-achieves")
    public ResponseEntity<?> getLowerAchieves(@RequestParam("achieve") String achieve) {
        if (StringUtils.isEmpty(achieve) || StringUtils.isBlank(achieve)) {
            throw new InvalidParamException("Validation failed");
        }
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(extraFunctionsService.getLowerAchieves(achieve));
    }

    @GetMapping("/pattern")
    public ResponseEntity<?> getByPattern(@RequestParam("field") String field, @RequestParam("value") String value) {
        if (StringUtils.isEmpty(field)) {
            throw new InvalidParamException("Validation failed");
        } else {
            List<String> values = Stream.of(AvailablePatternFields.values())
                    .map(Enum::toString)
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());
            if (!values.contains(field.toLowerCase())) {
                throw new InvalidParamException("Validation failed");
            }
        }
        if (StringUtils.isEmpty(value)) {
            throw new InvalidParamException("Validation failed");
        }
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(extraFunctionsService.getByPattern(field, value));
    }

    @GetMapping("/unique/heart")
    public ResponseEntity<?> getUniqueHeart() {
        UniqueHeart uniqueHeartCount = extraFunctionsService.getUniqueHeartCount();
        if (uniqueHeartCount.getHearts().isEmpty()) {
            throw new NotFoundException("Not found");
        }
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(extraFunctionsService.getUniqueHeartCount().getHearts());
    }
}
