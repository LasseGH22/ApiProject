package com.example.ApiProject.BitcoinReading;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("btc_readings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reading {
    @Id
    private Long id;
    private String dateTime;
    private String name;
    private float priceUSD;
    private float priceGBP;
    private float priceEUR;

    public Reading(String dateTime, String name, float priceUSD, float priceGBP, float priceEUR) {
        this.dateTime = dateTime;
        this.name = name;
        this.priceUSD = priceUSD;
        this.priceGBP = priceGBP;
        this.priceEUR = priceEUR;
    }
}
