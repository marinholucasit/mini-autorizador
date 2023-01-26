package com.lm.miniautorizador.service;

import com.lm.miniautorizador.dto.request.TransactonRequest;
import com.lm.miniautorizador.model.Transaction;
import com.lm.miniautorizador.utils.MessageTransaction;
import org.springframework.http.HttpStatusCode;

import java.util.Map;

public interface TransactionService {
    MessageTransaction performTransaction(Transaction transaction);
}
