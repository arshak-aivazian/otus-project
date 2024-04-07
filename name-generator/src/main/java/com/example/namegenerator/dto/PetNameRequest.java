package com.example.namegenerator.dto;

import com.example.namegenerator.dto.filter.Filter;
import lombok.Data;

@Data
public class PetNameRequest {
    private String userName;
    private Filter filter;
}
