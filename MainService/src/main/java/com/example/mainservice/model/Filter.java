package com.example.mainservice.model;

import com.example.mainservice.entity.AvailableFields;
import com.example.mainservice.entity.FilterOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Filter {
    private AvailableFields field;
    private FilterOperation operation;
    private String value;
}
