/*
package com.example.ApiProject;

import org.springframework.stereotype.Controller;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Controller
public class ClientConnection extends TextWebSocketHandler {
    public void connect() {
        StandardWebSocketClient client = new StandardWebSocketClient();
        WebSocketHandler handler = new TextWebSocketHandler() {
            @Override
            public void afterConnectionEstablished(WebSocketSession session) throws Exception {
                String message = "{\"type\":\"subscribe\",\"symbol\":\"BINANCE:BTCUSDT\"}";
                session.sendMessage(new TextMessage(message));
            }

            @Override
            protected void handleTextMessage(WebSocketSession session, TextMessage message) {
                System.out.println("Received message: ");
                for (String text : message.getPayload().split("\\}")) {
                    System.out.println(text);
                }
            }
        };

        String ConnectionUrl = "wss://ws.finnhub.io?token=cnerfuhr01qq13fnv4n0cnerfuhr01qq13fnv4ng";
        WebSocketConnectionManager manager = new WebSocketConnectionManager(client, handler, ConnectionUrl);
        manager.setAutoStartup(true);

        // Start the connection
        manager.start();
    }
}

 */


