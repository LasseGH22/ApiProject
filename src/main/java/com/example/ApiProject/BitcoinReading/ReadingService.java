package com.example.ApiProject.BitcoinReading;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public List<Reading> findTop40Reversed() {
        PageRequest pageRequest = PageRequest.of(0,40);
        List<Reading> entries = new ArrayList<>(readingRepository.findAllByOrderByIdDesc(pageRequest).getContent());
        Collections.reverse(entries);
        return entries;
    }
}
