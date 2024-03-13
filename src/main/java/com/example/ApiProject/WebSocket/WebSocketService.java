package com.example.ApiProject.WebSocket;

import com.example.ApiProject.BitcoinReading.Reading;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public WebSocketService(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void send(Reading reading) {
        simpMessagingTemplate.convertAndSend("/topic/latest", reading);
    }
}
