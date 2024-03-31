package com.example.namegenerator.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchCriteria implements Filter {
    private final String type = "SEARCH";

    private String key;
    private String operation;
    private Object value;
}
