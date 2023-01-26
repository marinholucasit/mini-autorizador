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
        String message = "";
        message += validate(new PasswordValidation(cardRepository), transaction);

        if (message.isEmpty()) {
            return new MessageTransaction("OK", HttpStatus.CREATED);
        } else {
            return new MessageTransaction(message, HttpStatus.UNPROCESSABLE_ENTITY);
        }



//        String message = "";
//        Optional<Card> card = cardRepository.findById(transaction.getCardNumber());
//        verifyCardExists(card);
//        verifyPassword(transaction.getCardPassword(), card.get());
//        verifyEnoughBalance(transaction.getTransactionAmount(), card.get());
//        if (message.isEmpty()) {
//            deductBalance(transaction.getTransactionAmount(), card.get());
//            return new MessageTransaction("OK", HttpStatus.CREATED);
//        } else {
//            return new MessageTransaction(message, HttpStatus.UNPROCESSABLE_ENTITY);
//        }
    }

    private String validate(Validator validator, Transaction transaction) {
        return validator.validation(transaction);
    }

    private void verifyCardExists(Optional<Card> card) {
        //this.message += (card.isEmpty())? "|CARTAO_INEXISTENTE": "";
    }

    private void verifyPassword(String password, Card card) {
        //this.message += (password != card.getPasswordCard())? "|SENHA_INVALIDA": "";
    }

    private void verifyEnoughBalance(Double amountTransaction, Card card) {
        //this.message += (amountTransaction > card.getBalance())? "|SALDO_INSUFICIENTE": "";
    }

    private void deductBalance(Double amountTransaction, Card card) {
        card.setBalance(card.getBalance() - amountTransaction);
        cardRepository.save(card);
    }
}
