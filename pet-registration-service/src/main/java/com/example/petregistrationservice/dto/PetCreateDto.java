package com.example.petregistrationservice.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PetCreateDto {
    private String species;
    private String breed;
    private String name;
    private String color;
    private String gender;
    private String description;
    private String createdBy;
    private MultipartFile image;
}
