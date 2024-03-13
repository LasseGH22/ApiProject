package com.example.ApiProject.WebSocket;

import com.example.ApiProject.BitcoinReading.Reading;
import com.example.ApiProject.BitcoinReading.ReadingService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    private final WebSocketService webSocketService;
    public WebSocketController(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }
}
