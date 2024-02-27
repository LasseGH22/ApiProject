package com.example.ApiProject;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebsocketClient extends TextWebSocketHandler {
    public void connect() {
        // Create a new StandardWebSocketClient
        StandardWebSocketClient client = new StandardWebSocketClient();

        // Create the WebSocket handler
        WebSocketHandler handler = new TextWebSocketHandler() {
            @Override
            public void afterConnectionEstablished(WebSocketSession session) throws Exception {
                // Message to send after the connection is established, e.g., to subscribe to a feed
                String message = "{\"type\":\"subscribe\",\"symbol\":\"AAPL\"}"; // Adjust based on required format
                session.sendMessage(new TextMessage(message));
            }

            @Override
            protected void handleTextMessage(WebSocketSession session, TextMessage message) {
                // Handle incoming messages
                System.out.println("Received message: " + message.getPayload());
            }
        };

        // Replace "your_finnhub_websocket_url" with the actual WebSocket URL from Finnhub
        String finnhubUrl = "wss://ws.finnhub.io?token=cnerfuhr01qq13fnv4n0cnerfuhr01qq13fnv4ng";

        // Create a new WebSocketConnectionManager
        WebSocketConnectionManager manager = new WebSocketConnectionManager(client, handler, finnhubUrl);
        // Automatically start the connection
        manager.setAutoStartup(true);

        // Start the connection
        manager.start();
    }
}


