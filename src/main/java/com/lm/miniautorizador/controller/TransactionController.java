package com.lm.miniautorizador.controller;

import com.lm.miniautorizador.dto.request.TransactonRequest;
import com.lm.miniautorizador.service.TransactionService;
import com.lm.miniautorizador.utils.MessageTransaction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/transacoes")
@Tag(name = "Transações", description = "Controller Transações")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping(produces = { "application/json" })
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Realizar uma transação")
    public ResponseEntity<String> transact(@RequestBody @Valid TransactonRequest transactionRequest) {
        MessageTransaction response = transactionService.performTransaction(transactionRequest.mapToTransaction());
        return ResponseEntity.status(response.getStatusCode()).body(response.getMessage());
    }
}
