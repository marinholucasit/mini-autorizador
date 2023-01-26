package com.lm.miniautorizador.service;

import com.lm.miniautorizador.model.Transaction;

public interface Validator<T> {
    String validation(Transaction transaction);
}
