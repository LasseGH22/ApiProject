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

    @GetMapping()
    public Reading putBtcData() {
        return readingService.fetchBtcData();
    }

    @PostMapping
    public String save(@RequestBody Reading reading) {
        return readingService.save(reading);
    }
}
