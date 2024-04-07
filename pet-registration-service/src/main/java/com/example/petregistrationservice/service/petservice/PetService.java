package com.example.petregistrationservice.service.petservice;

import com.example.petregistrationservice.dto.PetCreateDto;
import com.example.petregistrationservice.dto.PetEditDto;
import com.example.petregistrationservice.dto.PetReadDto;

import java.util.List;
import java.util.Optional;

public interface PetService {
    List<PetReadDto> findAll();

    PetReadDto findById(Long id);

    PetReadDto create(PetCreateDto petDto);

    PetReadDto update(PetEditDto petDto);

    boolean deletePet(Long id);

    Optional<byte[]> findAvatar(Long id);
}
