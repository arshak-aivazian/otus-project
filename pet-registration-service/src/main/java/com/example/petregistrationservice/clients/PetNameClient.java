package com.example.petregistrationservice.clients;

import com.example.petregistrationservice.dto.PetNameDto;
import com.example.petregistrationservice.dto.PetNameRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "name-generator",url = "${app.name-generation.host}")
public interface PetNameClient {

    @RequestMapping(method = RequestMethod.POST, value = "${app.name-generation.endpoint}", consumes = "application/json")
    PetNameDto getRandomPetName(PetNameRequest request);
}
