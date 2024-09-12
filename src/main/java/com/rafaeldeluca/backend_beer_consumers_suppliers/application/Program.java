package com.rafaeldeluca.backend_beer_consumers_suppliers.application;

import com.rafaeldeluca.backend_beer_consumers_suppliers.entities.Beer;
import com.rafaeldeluca.backend_beer_consumers_suppliers.readers.BeerReader;
import com.rafaeldeluca.backend_beer_consumers_suppliers.services.BeerService;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Program {

    public Program() {
    }

    private void testConsumers(List<Beer> beerList) {
        // receive a data input and to do return anything
        // has to default methods (accept, andThen)

        System.out.println("\nPrinting without inform object type");
        beerList.forEach(beer -> System.out.println(beer));

        System.out.println("\nSugar sintaxe");
        beerList.forEach(System.out::println);

        System.out.println("\nforEach");
        for(Beer nickname : beerList) {
            System.out.println(nickname);
        }

        System.out.println("\nUsing consumer");
        final Consumer<Beer> beerConsumer = beer -> System.out.println(beer);
        beerList.forEach(beerConsumer);
    }

    private void testBeerService(List<Beer> beerList) {

        System.out.println("\nBeer Service");
        final Beer beer  = BeerService.getBeerFromFile("Any beer");
        if(beer !=null) {
            System.out.println(beer);
        } else {
            System.out.println("No beer was found on the file database");
        }
    }

    private void testSupliers (List<Beer> beerList) {
        // supplier supplies value to a function
        // supllier make possivbel to produce data and return it
        // using Optinal it is not necessary to treat empty and blank responses
        // suplier is Lazzy
        final var beer = BeerService.getBeerFromFileUsingOptional("");
        System.out.println("\nUsing Supplier");
        final Supplier<Beer> beerSupplier = () -> beerList.get(1);
        //System.out.println(beer.orElse(beerList.get(0)));
        System.out.println(beer.orElseGet(beerSupplier));
    }

    public static void main(String[] args) {
        final List<Beer> beerList = new BeerReader("beers.json").streamToList();
        Program program = new Program();
        //program.testConsumers(beerList);
        //program.testBeerService(beerList);
        program.testSupliers(beerList);
    }
}
