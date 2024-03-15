package com.example.ApiProject.WebSocket;

import com.example.ApiProject.BitcoinReading.Reading;
import com.example.ApiProject.BitcoinReading.ReadingRepository;
import com.example.ApiProject.BitcoinReading.ReadingService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class WebSocketController {
    public WebSocketController(WebSocketService webSocketService, ReadingService readingService) {

    }
}
