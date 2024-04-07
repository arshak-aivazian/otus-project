package com.example.petregistrationservice.dto;

import lombok.Data;

@Data
public class PetReadDto {
    private Long id;
    private String species;
    private String breed;
    private String name;
    private String color;
    private String gender;
    private String description;
    private String image;
}
