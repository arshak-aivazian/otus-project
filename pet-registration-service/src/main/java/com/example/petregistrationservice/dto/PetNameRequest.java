package com.example.petregistrationservice.dto;

import com.example.petregistrationservice.dto.filter.Filter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetNameRequest {
    private String userName;
    private Filter filter;
}
