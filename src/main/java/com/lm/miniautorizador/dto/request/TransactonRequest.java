package com.lm.miniautorizador.dto.request;

import com.lm.miniautorizador.model.Transaction;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactonRequest {
    @NotNull
    @NotEmpty
    private String numeroCartao;
    @NotNull
    @NotEmpty
    private String senhaCartao;
    @NotNull
    private Double valor;

    public Transaction mapToTransaction() {
        return new Transaction(numeroCartao, senhaCartao, valor);
    }
}
