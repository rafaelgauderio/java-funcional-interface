package com.rafaeldeluca.backend_beer_consumers_suppliers.services;

import com.rafaeldeluca.backend_beer_consumers_suppliers.entities.Beer;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BeerService {

    public static Beer gerBeerFromFile(String filter) {
        if(filter.isBlank()) {
            return null;
        }
        return new Beer(1, UUID.randomUUID(), filter, filter,filter,filter,filter,filter,20,17,5);
    }
}
