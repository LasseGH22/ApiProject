package com.example.ApiProject.BitcoinReading;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ReadingService {

    private final RestTemplate restTemplate;

    @Autowired
    public ReadingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Reading> getReadings() {
        return List.of(
                new Reading(
                        "Mar 2, 2024 13:07:55 UTC",
                        "Bitcoin",
                        100F,
                        100F,
                        100F
                )
        );
    }

    private final String Api_Url = "https://api.coindesk.com/v1/bpi/currentprice.json";

    public Reading fetchBtcData() {
        String ApiResponse = restTemplate.getForObject(Api_Url,String.class);
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode JsonObject = mapper.readTree(ApiResponse);
            JsonNode date = JsonObject.path("time");
            JsonNode price = JsonObject.path("bpi");

            String dateTime = date.path("updated").asText();
            String name = JsonObject.path("chartName").asText();
            float priceUSD = price.path("USD").path("rate_float").floatValue();
            float priceGBP = price.path("GBP").path("rate_float").floatValue();
            float priceEUR = price.path("EUR").path("rate_float").floatValue();

            return new Reading(dateTime,name,priceUSD,priceGBP,priceEUR);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
