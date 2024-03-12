package com.example.ApiProject.BitcoinReading;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ReadingService {

    private final ReadingRepository readingRepository;

    public ReadingService(ReadingRepository readingRepository) {
        this.readingRepository = readingRepository;
    }

    @Transactional
    public Reading saveReading(Reading reading) {
        return readingRepository.save(reading);
    }

    public Reading getLatest() {
        return readingRepository.findFirstByOrderByIdDesc();
    }
}
