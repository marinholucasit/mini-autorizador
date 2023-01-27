package com.lm.miniautorizador.service.impl;

import com.lm.miniautorizador.entity.Card;
import com.lm.miniautorizador.model.Transaction;
import com.lm.miniautorizador.repository.CardRepository;
import com.lm.miniautorizador.service.TransactionService;
import com.lm.miniautorizador.service.Validator;
import com.lm.miniautorizador.utils.MessageTransaction;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public MessageTransaction performTransaction(Transaction transaction) {
        String message = validate(transaction);

        if (message.isEmpty()) {
            return new MessageTransaction("OK", HttpStatus.CREATED);
        } else {
            return new MessageTransaction(message, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    private String validate(Transaction transaction) {
        String message = "";
        message += new NonExistentCardValidation(cardRepository).validation(transaction);
        message += new PasswordValidation(cardRepository).validation(transaction);
        if (message.isEmpty()) {
            message += new InsufficientBalanceValidation(cardRepository).validation(transaction);
        }

        return message;
    }

    private void deductBalance(Double amountTransaction, Card card) {
        card.setBalance(card.getBalance() - amountTransaction);
        cardRepository.save(card);
    }
}
