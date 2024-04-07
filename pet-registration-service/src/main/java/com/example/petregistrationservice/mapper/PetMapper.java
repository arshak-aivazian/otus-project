package com.example.petregistrationservice.mapper;

import com.example.petregistrationservice.dto.PetCreateDto;
import com.example.petregistrationservice.dto.PetEditDto;
import com.example.petregistrationservice.dto.PetReadDto;
import com.example.petregistrationservice.entity.Pet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static java.util.function.Predicate.not;

@Mapper(componentModel = "spring")
public interface PetMapper {

    @Mapping(target = "image", source = "image", qualifiedByName = "getFileName")
    Pet toPet(PetCreateDto source);

    PetReadDto toPetReadDto(Pet source);

    @Named("getFileName")
    default String getFileName(MultipartFile image) {
        return Optional.ofNullable(image)
                .filter(not(MultipartFile::isEmpty))
                .map(MultipartFile::getOriginalFilename)
                .orElse(null);
    }

    default void copy(Pet entity, PetEditDto dto) {
        entity.setSpecies(dto.getSpecies());
        entity.setBreed(dto.getBreed());
        entity.setColor(entity.getColor());
        entity.setDescription(dto.getDescription());
        entity.setName(dto.getName());
    }
}
