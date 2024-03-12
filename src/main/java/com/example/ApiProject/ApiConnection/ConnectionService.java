package com.example.ApiProject.ApiConnection;

import com.example.ApiProject.BitcoinReading.Reading;
import com.example.ApiProject.BitcoinReading.ReadingRepository;
import com.example.ApiProject.BitcoinReading.ReadingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;
@Service
@EnableScheduling
public class ConnectionService {

    private final RestTemplate restTemplate;
    private final ReadingService readingService;

    public ConnectionService(RestTemplate restTemplate, ReadingService readingService) {
        this.restTemplate = restTemplate;
        this.readingService = readingService;
    }

    private final String Api_Url = "https://api.coindesk.com/v1/bpi/currentprice.json";

    @Scheduled(fixedDelay = 5,timeUnit = TimeUnit.MINUTES)
    public void fetchBtcData() {
        System.out.println("gemt");
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

            Reading reading = new Reading(dateTime, name, priceUSD, priceGBP, priceEUR);
            readingService.saveReading(reading);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
