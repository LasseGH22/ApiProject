package com.example.ApiProject.BitcoinReading;

import com.example.ApiProject.ApiConnection.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return readingService.getLatest();
    }

}
