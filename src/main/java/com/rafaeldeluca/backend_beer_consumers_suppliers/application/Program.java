package com.rafaeldeluca.backend_beer_consumers_suppliers.application;

import com.rafaeldeluca.backend_beer_consumers_suppliers.entities.Beer;
import com.rafaeldeluca.backend_beer_consumers_suppliers.readers.BeerReader;

import java.util.List;
import java.util.function.Consumer;

public class Program {

    public Program() {
    }

    private void testConsumers(List<Beer> beerList) {
        // receive a data input and to do return anything
        // has to default methods (accept, andThen)

        System.out.println("\nPrinting without inform object type");
        beerList.forEach(beer -> System.out.println(beer));

        System.out.println("\nUsing consumer");
        final Consumer<Beer> beerConsumer = beer -> System.out.println(beer);
        beerList.forEach(beerConsumer);

        System.out.println("\nSugar sintaxe");
        beerList.forEach(System.out::println);

        System.out.println("\nforEach");
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
