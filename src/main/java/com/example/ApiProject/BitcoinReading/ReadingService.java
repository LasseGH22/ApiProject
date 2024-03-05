package com.example.ApiProject.BitcoinReading;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ReadingService {

    private final RestTemplate restTemplate;
    private final ReadingRepository readingRepository;

    @Autowired
    public ReadingService(RestTemplate restTemplate, ReadingRepository readingRepository) {
        this.restTemplate = restTemplate;
        this.readingRepository = readingRepository;
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

            Reading reading = new Reading(dateTime,name,priceUSD,priceGBP,priceEUR);
            save(reading);
            return reading;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String save(Reading reading) {
        return readingRepository.save(reading).getName();
    }
}
