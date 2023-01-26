package com.lm.miniautorizador.service.impl;

import com.lm.miniautorizador.entity.Card;
import com.lm.miniautorizador.model.Transaction;
import com.lm.miniautorizador.repository.CardRepository;
import com.lm.miniautorizador.service.TransactionService;
import com.lm.miniautorizador.utils.MessageTransaction;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final CardRepository cardRepository;
    @Override
    public MessageTransaction performTransaction(Transaction transaction) {
        Card card = cardRepository.findById(transaction.getCardNumber()).get();
        deductBalance(transaction.getTransactionAmount(), card);
        return new MessageTransaction("OK", HttpStatus.CREATED);
    }

    private void deductBalance(Double amountTransaction, Card card) {
        card.setBalance(card.getBalance() - amountTransaction);
        cardRepository.save(card);
    }
}
