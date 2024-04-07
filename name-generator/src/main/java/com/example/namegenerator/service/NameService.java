package com.example.namegenerator.service;

import com.example.namegenerator.dto.PetNameRequest;
import com.example.namegenerator.entity.PetName;

public interface NameService {
    PetName getRandomName(PetNameRequest request);
}
