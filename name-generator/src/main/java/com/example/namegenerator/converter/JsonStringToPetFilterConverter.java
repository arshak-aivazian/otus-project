package com.example.namegenerator.converter;

import com.example.namegenerator.dto.filter.Filter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
class JsonStringToPetFilterConverter implements Converter<String, Filter> {

    private final ObjectMapper jsonMapper = new ObjectMapper();

    @SneakyThrows
    public Filter convert(String source) {
        return jsonMapper.readerFor(Filter.class).readValue(source);
    }
}
