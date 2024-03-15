package com.example.ApiProject.WebSocket;

import com.example.ApiProject.BitcoinReading.Reading;
import com.example.ApiProject.BitcoinReading.ReadingService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebSocketService {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ReadingService readingService;

    public WebSocketService(SimpMessagingTemplate simpMessagingTemplate, ReadingService readingService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.readingService = readingService;
    }

    public void sendLatest(Reading reading) {
        simpMessagingTemplate.convertAndSend("/topic/latest", reading);
    }


    public void sendTop40Reversed() {
        simpMessagingTemplate.convertAndSend("/topic/top40Reversed", readingService.findTop40Reversed());
    }
}
