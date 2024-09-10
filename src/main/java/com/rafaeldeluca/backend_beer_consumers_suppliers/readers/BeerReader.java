package com.rafaeldeluca.backend_beer_consumers_suppliers.readers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rafaeldeluca.backend_beer_consumers_suppliers.entities.Beer;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class BeerReader {

    final InputStream inputStream;

    public BeerReader(String dataJsonFile) {
        this.inputStream = this.getClass().getClassLoader().getResourceAsStream(dataJsonFile);
    }

    public List<Beer> streamToList() {

        if (inputStream == null) {
            return List.of();
        }
        final ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.disable(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES,
                DeserializationFeature.FAIL_ON_INVALID_SUBTYPE
        );

        try {
            return objectMapper.readValue(inputStream, new TypeReference<List<Beer>>() {
            });
        } catch (IOException ioException) {
            ioException.printStackTrace();
            throw new RuntimeException("Can not read json File", ioException);
        }

    }

}
