package com.example.ApiProject.BitcoinReading;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("api/v1/reading")
public class ReadingController {
    private final ReadingService readingService;

    @Autowired
    public ReadingController (ReadingService readingService) {
        this.readingService = readingService;
    }
    @GetMapping
    public List<Reading> getReadings() {
        return readingService.getReadings();
    }

    @GetMapping("btcData")
    public Reading putBtcData() {
        return readingService.fetchBtcData();
    }
}
