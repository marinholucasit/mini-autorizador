package com.lm.miniautorizador.service.impl;

import com.lm.miniautorizador.dto.request.TransactonRequest;
import com.lm.miniautorizador.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    @Override
    public String performTransaction(TransactonRequest transactonRequest) {
        return null;
    }
}
