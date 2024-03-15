package com.example.ApiProject.WebSocket;

import com.example.ApiProject.BitcoinReading.Reading;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebSocketService {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public WebSocketService(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void sendLatest(Reading reading) {
        simpMessagingTemplate.convertAndSend("/topic/latest", reading);
    }

    /*
    public void sendTop40Reversed(List<Reading> readings) {
        simpMessagingTemplate.convertAndSend("/topic/top40Reversed", readings);
    }

     */
}
