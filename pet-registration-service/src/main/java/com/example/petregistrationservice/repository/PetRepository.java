package com.example.petregistrationservice.repository;

import com.example.petregistrationservice.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
