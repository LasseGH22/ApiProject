package com.example.ApiProject.ApiConnection;

import com.example.ApiProject.BitcoinReading.Reading;
import com.example.ApiProject.BitcoinReading.ReadingService;
import com.example.ApiProject.WebSocket.WebSocketService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;
@Service
@EnableScheduling
public class ConnectionHandler {

    private final RestTemplate restTemplate;
    private final ReadingService readingService;
    private final WebSocketService webSocketService;

    public ConnectionHandler(RestTemplate restTemplate, ReadingService readingService, WebSocketService webSocketService) {
        this.restTemplate = restTemplate;
        this.readingService = readingService;
        this.webSocketService = webSocketService;
    }

    private final String Api_Url = "https://api.coindesk.com/v1/bpi/currentprice.json";

    @Scheduled(fixedDelay = 5,timeUnit = TimeUnit.SECONDS)
    public void fetchBtcData() {
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
            webSocketService.send(reading);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
