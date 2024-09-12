package com.rafaeldeluca.backend_beer_consumers_suppliers.entities;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.UUID;

/**
 * @author Rafael de Luca
 */

@Data
public class Beer {

    private final int id;
    private final UUID uid;
    private final String brand;
    private final String name;
    private final String style;
    private final String hop;
    private final String yeast;
    private final String malts;
    private final int ibu;
    private final double alcohol;
    private final double ballingScale;

    @JsonCreator
    public Beer(@JsonProperty("id") int id, @JsonProperty("uid") UUID uid,
                @JsonProperty("brand") String brand, @JsonProperty("name") String name,
                @JsonProperty("style") String style, @JsonProperty("hop") String hop,
                @JsonProperty("yeast") String yeast, @JsonProperty("malts") String malts,
                @JsonProperty("ibu") int ibu, @JsonProperty("alcohol") double alcohol,
                @JsonProperty("blg") double ballingScale) {
        this.id = id;
        this.uid = uid;
        this.brand = brand;
        this.name = name;
        this.style = style;
        this.hop = hop;
        this.yeast = yeast;
        this.malts = malts;
        this.ibu = ibu;
        this.alcohol = alcohol;
        this.ballingScale = ballingScale;
    }

    @Override
    public String toString() {
        return "Beer{ " +
                "id=" + id +
                ", uid=" + uid +
                ", brand='" + brand + '\'' +
                ", name='" + name + '\'' +
                ", alcohol content=" + alcohol +
                '}';
    }

    public boolean alcoholContentHigherThanEight() {
        return this.getAlcohol() > 8.0;
    }
}
