package com.example.petregistrationservice.service.petservice;

import com.example.petregistrationservice.dto.PetCreateDto;
import com.example.petregistrationservice.dto.PetEditDto;
import com.example.petregistrationservice.dto.PetReadDto;
import com.example.petregistrationservice.entity.Pet;
import com.example.petregistrationservice.mapper.PetMapper;
import com.example.petregistrationservice.repository.PetRepository;
import com.example.petregistrationservice.service.image.ImageService;
import com.example.petregistrationservice.service.namegenerator.PetNameGenerator;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final ImageService imageService;
    private final PetMapper petMapper;
    private final PetNameGenerator petNameGenerator;

    @Transactional(readOnly = true)
    @Override
    public List<PetReadDto> findAll() {
        return petRepository.findAll().stream()
                .map(petMapper::toPetReadDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public PetReadDto findById(Long id) {
        return petRepository.findById(id)
                .map(petMapper::toPetReadDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public PetReadDto create(PetCreateDto petDto) {
        if (!StringUtils.hasText(petDto.getName())) {
            String petName = petNameGenerator.generateName(petDto.getGender(), petDto.getSpecies(), petDto.getCreatedBy());
            petDto.setName(petName);
        }

        return Optional.of(petDto)
                .map(dto -> {
                    uploadImage(petDto.getImage());
                    return petMapper.toPet(dto);
                })
                .map(petRepository::save)
                .map(petMapper::toPetReadDto)
                .orElseThrow();
    }

    @Override
    public PetReadDto update(PetEditDto petDto) {
        return petRepository.findById(petDto.getId())
                .map(entity -> {
                    petMapper.copy(entity, petDto);
                    return entity;
                })
                .map(petRepository::saveAndFlush)
                .map(petMapper::toPetReadDto)
                .orElseThrow();
    }

    @Override
    public boolean deletePet(Long id) {
        return petRepository.findById(id)
                .map(entity -> {
                    petRepository.delete(entity);
                    petRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    @Override
    public Optional<byte[]> findAvatar(Long id) {
        return petRepository.findById(id)
                .map(Pet::getImage)
                .filter(StringUtils::hasText)
                .flatMap(imageService::get);
    }

    @SneakyThrows
    private void uploadImage(MultipartFile image) {
        if (!image.isEmpty()) {
            imageService.upload(image.getOriginalFilename(), image.getInputStream());
        }
    }
}
