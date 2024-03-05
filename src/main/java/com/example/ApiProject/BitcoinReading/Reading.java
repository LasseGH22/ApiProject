package com.example.ApiProject.BitcoinReading;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("btc_readings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reading {
    private String dateTime;
    private String name;
    private float priceUSD;
    private float priceGBP;
    private float priceEUR;
}
