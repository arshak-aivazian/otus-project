package com.example.namegenerator.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AndFilter implements Filter {
    private final String type = "AND";

    private List<Filter> value;
}
