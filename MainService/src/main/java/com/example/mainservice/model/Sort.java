package com.example.mainservice.model;

import com.example.mainservice.entity.AvailableSortFields;
import com.example.mainservice.entity.SortOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sort {
    private AvailableSortFields field;
    private SortOperation operation;
}
