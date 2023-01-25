package com.lm.miniautorizador.integration;

import com.lm.miniautorizador.dto.request.CardRequest;
import com.lm.miniautorizador.dto.response.CardResponse;
import com.lm.miniautorizador.service.CardService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CardControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Mock
    private CardService cardService;

    @Test
    public void ShouldCreateCardAndValidRequestReturns201() {
        CardRequest cardRequest = new CardRequest("123456789", "senha123");
        ResponseEntity<CardResponse> response = restTemplate.postForEntity("/cartoes", cardRequest, CardResponse.class);
        assertEquals("Response status code: "+response.getStatusCode(), HttpStatus.CREATED, response.getStatusCode());
        CardResponse cardResponse = response.getBody();
        assertEquals("field cardNumber: "+cardRequest.getNumeroCartao(), "123456789", cardResponse.getNumeroCartao());
        assertEquals("field password: "+cardRequest.getNumeroCartao(), "123456789", cardResponse.getNumeroCartao());
    }

    @Test
    public void ShouldReturns400WhenCardNumberIsEmpty() {
        CardRequest cardRequest = new CardRequest("", "senha123");
        ResponseEntity<CardResponse> response = restTemplate.postForEntity("/cartoes", cardRequest, CardResponse.class);
        assertEquals("Response status code: "+response.getStatusCode(), HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void ShouldReturns400WhenPasswordIsEmpty() {
        CardRequest cardRequest = new CardRequest("123456", "");
        ResponseEntity<CardResponse> response = restTemplate.postForEntity("/cartoes", cardRequest, CardResponse.class);
        assertEquals("Response status code: "+response.getStatusCode(), HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
