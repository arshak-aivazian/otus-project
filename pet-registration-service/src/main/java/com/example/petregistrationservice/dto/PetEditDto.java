package com.example.petregistrationservice.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PetEditDto {
    private Long id;
    private String species;
    private String breed;
    private String name;
    private String color;
    private String gender;
    private String description;
}
