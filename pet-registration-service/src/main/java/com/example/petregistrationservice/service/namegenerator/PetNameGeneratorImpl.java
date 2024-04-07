package com.example.petregistrationservice.service.namegenerator;

import com.example.petregistrationservice.clients.PetNameClient;
import com.example.petregistrationservice.dto.PetNameRequest;
import com.example.petregistrationservice.dto.filter.AndFilter;
import com.example.petregistrationservice.dto.filter.SearchCriteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PetNameGeneratorImpl implements PetNameGenerator {
    private final static String GENDER_KEY = "gender";
    private final static String SPECIES_KEY = "species";
    private final static String EQUAL_OPERATION = "EQ";

    private final PetNameClient petNameClient;

    @Override
    public String generateName(String gender, String species, String userName) {
        var request = prepareRequest(gender, species, userName);
        var petNameDto = petNameClient.getRandomPetName(request);
        return petNameDto.getName();
    }

    private PetNameRequest prepareRequest(String gender, String species, String userName) {
        var andFilter = AndFilter.builder()
                .value(List.of(
                        SearchCriteria.builder().key(GENDER_KEY).value(gender).operation(EQUAL_OPERATION).build(),
                        SearchCriteria.builder().key(SPECIES_KEY).value(species).operation(EQUAL_OPERATION).build()
                ))
                .build();
        var petNameRequest = new PetNameRequest(userName, andFilter);

        log.info("Name generation request {}", petNameRequest);

        return petNameRequest;
    }
}
