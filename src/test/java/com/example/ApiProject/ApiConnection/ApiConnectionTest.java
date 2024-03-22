package com.example.ApiProject.ApiConnection;

import com.example.ApiProject.BitcoinReading.Reading;
import com.example.ApiProject.BitcoinReading.ReadingService;
import com.example.ApiProject.WebSocket.WebSocketService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;
import static org.mockito.Mockito.*;

public class ApiConnectionTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ReadingService readingService;

    @Mock
    private WebSocketService webSocketService;

    @Mock
    private ConnectionService connectionService;

    @InjectMocks
    private ConnectionHandler connectionHandler;

    public ApiConnectionTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFetchBtcData() throws Exception {
        // Arrange
        String fakeApiResponse = "{\"time\":{\"updated\":\"Test Date\"},\"chartName\":\"Bitcoin\",\"bpi\":{\"USD\":{\"rate_float\":12345.67},\"GBP\":{\"rate_float\":23456.78},\"EUR\":{\"rate_float\":34567.89}}}";
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(fakeApiResponse);
        when(connectionService.splitTime(anyString())).thenReturn(new String[]{"2024", "03", "22", "12:00:00"});

        // Act
        connectionHandler.fetchBtcData();

        // Assert
        verify(restTemplate).getForObject("https://api.coindesk.com/v1/bpi/currentprice.json", String.class);
        verify(connectionService).splitTime("Test Date");
        verify(readingService).saveReading(any(Reading.class));
        verify(webSocketService).sendLatest(any(Reading.class));
        verify(webSocketService).sendTop40Reversed();
    }
}

