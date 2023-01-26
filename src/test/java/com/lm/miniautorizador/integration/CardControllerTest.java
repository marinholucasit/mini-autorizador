package com.lm.miniautorizador.integration;

import com.lm.miniautorizador.controller.CardController;
import com.lm.miniautorizador.dto.request.CardRequest;
import com.lm.miniautorizador.dto.response.CardResponse;
import com.lm.miniautorizador.repository.CardRepository;
import com.lm.miniautorizador.service.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.reset;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@ActiveProfiles("test")
public class CardControllerTest {

    @InjectMocks
    private CardController cardController;
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CardRepository cardRepository;

    @Mock
    private CardService cardService;

    @BeforeEach
    void beforeEach() {
        reset();
    }

    @Test
    public void ShouldCreateCardAndValidRequestReturns201() {
        CardRequest cardRequest = new CardRequest("6549873025634518", "senha28");
        ResponseEntity<CardResponse> response = restTemplate.postForEntity("/cartoes", cardRequest, CardResponse.class);
        assertEquals("Response status code: "+response.getStatusCode(), HttpStatus.CREATED, response.getStatusCode());
        CardResponse cardResponse = response.getBody();
        assertEquals("field cardNumber: "+cardRequest.getNumeroCartao(), "6549873025634518", cardResponse.getNumeroCartao());
        assertEquals("field password: "+cardRequest.getSenha(), "senha28", cardResponse.getSenha());
    }

    @Test
    public void ShouldReturns400WhenCardNumberIsEmpty() {
        CardRequest cardRequest = new CardRequest("", "senha38");
        ResponseEntity<CardResponse> response = restTemplate.postForEntity("/cartoes", cardRequest, CardResponse.class);
        assertEquals("Response status code: "+response.getStatusCode(), HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void ShouldReturns400WhenPasswordIsEmpty() {
        CardRequest cardRequest = new CardRequest("6549873025634545", "");
        ResponseEntity<CardResponse> response = restTemplate.postForEntity("/cartoes", cardRequest, CardResponse.class);
        assertEquals("Response status code: "+response.getStatusCode(), HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void ShouldReturns200ToGetBalanceEqual500() {
        CardRequest cardRequest = new CardRequest("6549873025634552", "test52");
        restTemplate.postForEntity("/cartoes", cardRequest, CardResponse.class);
        ResponseEntity<Double> response = restTemplate.getForEntity("/cartoes/6549873025634552", Double.class);
        assertEquals("Response status code: "+response.getStatusCode(), HttpStatus.OK, response.getStatusCode());
        assertEquals("Response balance value: "+response.getBody(), 500.00, response.getBody());
    }

    @Test
    public void ShouldReturns404WhenNotFound() {
        ResponseEntity<Double> response = restTemplate.getForEntity("/cartoes/6549873025634561", Double.class);
        assertEquals("Response status code: "+response.getStatusCode(), HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void ShouldReturns422WhenCardExists() {
        CardRequest cardRequest = new CardRequest("6549873025634567", "test67");
        restTemplate.postForEntity("/cartoes", cardRequest, CardResponse.class);
        CardRequest cardexists = new CardRequest("6549873025634567", "test67");
        ResponseEntity<CardResponse> response = restTemplate.postForEntity("/cartoes", cardRequest, CardResponse.class);
        assertEquals("Response status code: "+response.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    }

}
