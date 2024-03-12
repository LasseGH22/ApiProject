package com.example.ApiProject.BitcoinReading;

import com.example.ApiProject.ApiConnection.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadingService {

    private final ReadingRepository readingRepository;

    public ReadingService(ReadingRepository readingRepository) {
        this.readingRepository = readingRepository;
    }

    public Reading saveReading(Reading reading) {
        return readingRepository.save(reading);
    }

    public Reading getLatest() {
        return readingRepository.findFirstByOrderByIdDesc();
    }
}
