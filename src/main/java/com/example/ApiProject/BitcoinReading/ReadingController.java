package com.example.ApiProject.BitcoinReading;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/reading")
public class ReadingController {
    private final ReadingService readingService;

    @Autowired
    public ReadingController (ReadingService readingService) {
        this.readingService = readingService;
    }

    @GetMapping("latest")
    public Reading getLatestBtcData() {
        return readingService.getLatest();
    }

    @GetMapping("readings")
    public List<Reading> getReadings() {
        return readingService.findAll();
    }
}
