package com.lm.miniautorizador.controller;

import com.lm.miniautorizador.dto.request.CardRequest;
import com.lm.miniautorizador.dto.response.CardResponse;
import com.lm.miniautorizador.entity.Card;
import com.lm.miniautorizador.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@AllArgsConstructor
@RequestMapping("/cartoes")
@Tag(name = "Cartões", description = "Controller Cartões")
public class CardController {

    private final CardService cardService;

    @PostMapping(produces = { "application/json" })
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar um novo cartão")
    public ResponseEntity<CardResponse> createCard(@Valid @RequestBody CardRequest cardRequest, UriComponentsBuilder uriBuilder) {
        Card card = cardService.save(cardRequest.mapToCard());
        URI uri =  uriBuilder.path("/cartoes/{numeroCartao}").buildAndExpand(card.getCardNumber()).toUri();
        return ResponseEntity.created(uri).body(new CardResponse(card));
    }

    @GetMapping("{numeroCartao}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar um cartão pelo seu número")
    public ResponseEntity<CardResponse> getCard(@PathVariable String numeroCartao) {
        return cardService.getCardByNumber(numeroCartao).map(
                card -> ResponseEntity.ok(new CardResponse(card)))
                .orElse(ResponseEntity.notFound().build());
    }

}
