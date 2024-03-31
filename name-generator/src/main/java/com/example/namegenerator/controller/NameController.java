package com.example.namegenerator.controller;

import com.example.namegenerator.dto.PetNameRequest;
import com.example.namegenerator.entity.PetName;
import com.example.namegenerator.service.NameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/generate")
@RequiredArgsConstructor
public class NameController {

    private final NameService nameService;

    @PostMapping
    public PetName generateName(@RequestBody PetNameRequest request) {
        return nameService.getRandomName(request);
    }
}
