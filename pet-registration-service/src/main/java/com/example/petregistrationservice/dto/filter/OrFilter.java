package com.example.petregistrationservice.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrFilter implements Filter {
    private final String type = "OR";

    private List<Filter> value;
}
