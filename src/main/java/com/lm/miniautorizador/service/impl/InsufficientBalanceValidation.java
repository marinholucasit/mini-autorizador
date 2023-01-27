package com.lm.miniautorizador.service.impl;

import com.lm.miniautorizador.entity.Card;
import com.lm.miniautorizador.model.Transaction;
import com.lm.miniautorizador.repository.CardRepository;
import com.lm.miniautorizador.service.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class InsufficientBalanceValidation implements Validator<String> {

    private final CardRepository cardRepository;

    @Override
    public String validation(Transaction transaction) {
        Optional<Card> cardOptional = cardRepository.findById(transaction.getCardNumber());
        return cardOptional
                .filter(card -> card.getBalance() >= transaction.getTransactionAmount())
                .map(card -> "")
                .orElse("SALDO_INSUFICIENTE|");
    }
}
