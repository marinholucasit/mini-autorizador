package com.lm.miniautorizador.service.impl;

import com.lm.miniautorizador.entity.Card;
import com.lm.miniautorizador.model.Transaction;
import com.lm.miniautorizador.repository.CardRepository;
import com.lm.miniautorizador.service.TransactionService;
import com.lm.miniautorizador.utils.MessageTransaction;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public MessageTransaction performTransaction(Transaction transaction) {
        log.info("Starting transaction to card: {}", transaction.getCardNumber());
        String message = validate(transaction);
        return message.isEmpty()?
                deductBalance(transaction.getTransactionAmount(),
                        cardRepository.findById(transaction.getCardNumber()).get()
                ):
                new MessageTransaction(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private String validate(Transaction transaction) {
        log.info("Running validations to card: {}", transaction.getCardNumber());
        String message = new NonExistentCardValidation(cardRepository).validation(transaction) +
                         new PasswordValidation(cardRepository).validation(transaction);
        return message.isEmpty()?new InsufficientBalanceValidation(cardRepository).validation(transaction): message;
    }

    private MessageTransaction deductBalance(Double amountTransaction, Card card) {
        log.info("Execute transaction to card: {}", card.getCardNumber());
        try {
            card.setBalance(card.getBalance() - amountTransaction);
            cardRepository.save(card);
            return new MessageTransaction("OK", HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("A Error occurred to update balance: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
