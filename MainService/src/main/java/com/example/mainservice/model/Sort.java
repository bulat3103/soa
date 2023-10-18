package com.example.mainservice.model;

import com.example.mainservice.entity.AvailableFields;
import com.example.mainservice.entity.SortOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sort {
    private AvailableFields field;
    private SortOperation operation;
}
