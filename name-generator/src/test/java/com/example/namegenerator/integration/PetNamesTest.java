package com.example.namegenerator.integration;

import com.example.namegenerator.dto.PetNameRequest;
import com.example.namegenerator.dto.filter.AndFilter;
import com.example.namegenerator.dto.filter.SearchCriteria;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PetNamesTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getPetNameByFilter() throws Exception {

        var request = createReqeuest();

        mockMvc.perform(post("/api/generate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(getJsonString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("dogMale"))
                .andExpect(jsonPath("gender").value("male"))
                .andExpect(jsonPath("species").value("dog"));
    }

    private PetNameRequest createReqeuest() {
        var andFilter = AndFilter.builder()
                .value(List.of(
                        SearchCriteria.builder().key("gender").value("male").operation("EQ").build(),
                        SearchCriteria.builder().key("species").value("dog").operation("EQ").build()
                ))
                .build();

        var request = new PetNameRequest();
        request.setUsername("admin");
        request.setFilter(andFilter);
        return request;
    }

    private String getJsonString(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }
}
