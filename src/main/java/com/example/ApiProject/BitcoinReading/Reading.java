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
    private String dateMonth;
    private String dateDay;
    private String dateYear;
    private String dateTime;
    private String name;
    private float priceUSD;
    private float priceGBP;
    private float priceEUR;

    public Reading(String dateMonth, String dateDay, String dateYear, String dateTime, String name, float priceUSD, float priceGBP, float priceEUR) {
        this.dateMonth = dateMonth;
        this.dateDay = dateDay;
        this.dateYear = dateYear;
        this.dateTime = dateTime;
        this.name = name;
        this.priceUSD = priceUSD;
        this.priceGBP = priceGBP;
        this.priceEUR = priceEUR;
    }

    @Override
    public String toString() {
        return "Reading{" +
                "dateMonth='" + dateMonth + '\'' +
                ", dateDay='" + dateDay + '\'' +
                ", dateYear='" + dateYear + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", name='" + name + '\'' +
                ", priceUSD=" + priceUSD +
                ", priceGBP=" + priceGBP +
                ", priceEUR=" + priceEUR +
                '}';
    }
}
