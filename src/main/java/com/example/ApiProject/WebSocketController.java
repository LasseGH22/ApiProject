package com.example.ApiProject;

import com.example.ApiProject.BitcoinReading.Reading;
import com.example.ApiProject.BitcoinReading.ReadingService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@Controller
public class WebSocketController {

    private final ReadingService readingService;

    public WebSocketController(ReadingService readingService) {
        this.readingService = readingService;
    }


    @MessageMapping("/latest")
    @SendTo("/topic/latest")
    public Reading getLatestBtcData() {
        return readingService.getLatest();
    }
}
