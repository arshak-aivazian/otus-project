package com.example.petregistrationservice.controller;

import com.example.petregistrationservice.dto.PetCreateDto;
import com.example.petregistrationservice.dto.PetEditDto;
import com.example.petregistrationservice.dto.PetReadDto;
import com.example.petregistrationservice.service.petservice.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pets")
public class PetController {

    private final PetService petService;

    @GetMapping
    public List<PetReadDto> findAll() {
        return petService.findAll();
    }

    @GetMapping("/{id}")
    public PetReadDto findById(@PathVariable("id") Long id) {
        return petService.findById(id);
    }

    @PostMapping(consumes = {"multipart/form-data"})
    @ResponseStatus(HttpStatus.CREATED)
    public PetReadDto createPet(@ModelAttribute PetCreateDto dto) {
        return petService.create(dto);
    }

    @PutMapping(consumes = {"multipart/form-data"})
    public PetReadDto editPet(@ModelAttribute PetEditDto dto) {
        return petService.update(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return petService.deletePet(id)
                ? noContent().build()
                : notFound().build();
    }

    @GetMapping(value = "/{id}/avatar")
    public ResponseEntity<byte[]> findAvatar(@PathVariable("id") Long id) {
        return petService.findAvatar(id)
                .map(content -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                        .contentLength(content.length)
                        .body(content))
                .orElseGet(notFound()::build);
    }
}
