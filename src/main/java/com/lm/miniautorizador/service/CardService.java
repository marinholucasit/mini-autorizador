package com.lm.miniautorizador.service;

import com.lm.miniautorizador.entity.Card;

import java.util.Optional;

public interface CardService {
    Card save(Card card);
    Optional<Card> getCardByNumber(String cardNumber);
}
