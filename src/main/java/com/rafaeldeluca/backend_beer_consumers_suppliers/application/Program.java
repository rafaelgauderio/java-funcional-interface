package com.rafaeldeluca.backend_beer_consumers_suppliers.application;

import com.rafaeldeluca.backend_beer_consumers_suppliers.entities.Beer;
import com.rafaeldeluca.backend_beer_consumers_suppliers.readers.BeerReader;
import com.rafaeldeluca.backend_beer_consumers_suppliers.services.BeerService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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
        for (Beer nickname : beerList) {
            System.out.println(nickname);
        }

        System.out.println("\nUsing consumer");
        final Consumer<Beer> beerConsumer = beer -> System.out.println(beer);
        beerList.forEach(beerConsumer);
    }

    private void testBeerService(List<Beer> beerList) {

        System.out.println("\nBeer Service");
        final Beer beer = BeerService.getBeerFromFile("Any beer");
        if (beer != null) {
            System.out.println(beer);
        } else {
            System.out.println("No beer was found on the file database");
        }
    }

    private void testSuppliers(List<Beer> beerList) {
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

    private void testPredicates(List<Beer> beerList) {
        // condition for something to happen
        // condition for a functional operation
        // use a condition as filter
        System.out.println("\nNot using Predicate");
        for (Beer nickname : beerList) {
            if (nickname.getAlcohol() > 8.5) {
                System.out.println(nickname);
            }
        }


        System.out.println("\nUsing Predicate");
        final Predicate<Beer> beerPredicate = (beer) -> beer.getAlcohol() > 8.5;
        // streams are immutable
        beerList.stream()
                .filter(beerPredicate)
                .forEach(System.out::println);

        System.out.println("\nDeclaring predicate in the filter (alcohol content less than 3.0)");
        beerList.stream()
                .filter(ceva -> ceva.getAlcohol() < 3.0)
                .forEach(System.out::println);

        System.out.println("\nUsing Predicate sintaxe sugar");
        beerList.stream()
                .filter(Beer::alcoholContentHigherThanEight)
                .forEach(System.out::println);

    }

    private void testFunctionalInterface(List<Beer> beerList) {
        // functional interface thar can transform data
        System.out.println("\nWithout Functional interface");
        //final List<Beer> malts = new ArrayList<>();
        final var malts = new ArrayList<>();
        for (Beer nickname : beerList) {
            final String malt = nickname.getMalts();
            // removing repeat malt
            if (malts.contains(malt) == false) {
                malts.add(malt);
            }
        }
        System.out.println(malts);

        System.out.println("\nUsing Functional Paradigm");
        beerList.stream()
                // transform Object Beer on malts
                .map(
                        beer -> {
                            return beer.getMalts() + ", ";
                        })
                .distinct()
                .collect(Collectors.toList())
                .forEach(System.out::printf);

        System.out.println("\n\nMore elegant code");
        beerList.stream()
                .map(Beer::getMalts)
                .map((beer) -> {
                    return (beer) + ", ";
                })
                .distinct()
                .collect(Collectors.toList())
                .forEach(System.out::print);
    }

    public static void main(String[] args) {
        final List<Beer> beerList = new BeerReader("beers.json").streamToList();
        Program program = new Program();
        //program.testConsumers(beerList);
        //program.testBeerService(beerList);
        //program.testSuppliers(beerList);
        //program.testPredicates(beerList);
        program.testFunctionalInterface(beerList);
    }
}
