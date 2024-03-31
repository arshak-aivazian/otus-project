package com.example.namegenerator.service;

import com.example.namegenerator.dto.PetNameRequest;
import com.example.namegenerator.dto.UpdateUserStatisticMessage;
import com.example.namegenerator.dto.filter.Filter;
import com.example.namegenerator.entity.PetName;
import com.example.namegenerator.error.PetNotFoundException;
import com.example.namegenerator.notification.NotificationService;
import com.example.namegenerator.repository.PetRepository;
import com.example.namegenerator.utility.SpecificationCreator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NameServiceImpl implements NameService {
    private static final String EVENT_NAME = "NAME_GENERATION";

    private final PetRepository petRepository;
    private final NotificationService notificationService;

    @Override
    public PetName getRandomName(PetNameRequest request) {
        List<PetName> names = request.getFilter() == null
                ? findAllPetNames()
                : findPetNamesByFilter(request.getFilter());

        if (names.isEmpty()) {
            throw new PetNotFoundException("pets names not found");
        }

        Collections.shuffle(names);
        PetName petName = names.get(0);

        sendMessage(request.getUsername(), petName.getName());
        return petName;
    }

    private List<PetName> findAllPetNames() {
        return petRepository.findAll();
    }

    private List<PetName> findPetNamesByFilter(Filter filter) {
        Specification<PetName> specification = SpecificationCreator.create(filter);
        return petRepository.findAll(specification);
    }

    private void sendMessage(String userName, String petName) {
        var message = UpdateUserStatisticMessage.builder()
                .userName(userName)
                .eventName(EVENT_NAME)
                .date(LocalDateTime.now())
                .body(petName)
                .build();

        notificationService.notify(message);
        log.info("name-generator generated message " + message);
    }
}
