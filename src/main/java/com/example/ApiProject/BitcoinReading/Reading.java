package com.example.ApiProject.BitcoinReading;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reading {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
