package com.lm.miniautorizador.service;

import com.lm.miniautorizador.dto.request.TransactonRequest;

public interface TransactionService {
    String performTransaction(TransactonRequest transactonRequest);
}
