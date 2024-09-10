package com.rafaeldeluca.backend_beer_consumers_suppliers.application;

import com.rafaeldeluca.backend_beer_consumers_suppliers.entities.Beer;
import com.rafaeldeluca.backend_beer_consumers_suppliers.readers.BeerReader;

import java.util.List;
import java.util.function.Consumer;

public class Program {

    public Program() {
    }

    private void testConsumers(List<Beer> beerList) {
        final Consumer<Beer> beerConsumer = beer -> System.out.println(beer);

        System.out.println("\nforEach");
        beerList.forEach(beerConsumer);
        System.out.println("\nfor");
        for(Beer nickname : beerList) {
            System.out.println(nickname);
        }
    }

    public static void main(String[] args) {
        final List<Beer> beerList = new BeerReader("beers.json").streamToList();
        Program program = new Program();
        program.testConsumers(beerList);
    }
}
