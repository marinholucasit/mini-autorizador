package com.lm.miniautorizador.dto.request;

import com.lm.miniautorizador.model.Transaction;
import jakarta.validation.constraints.Digits;
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
    @Digits(integer = 100, fraction = 0, message = "{cardNumber.only.number}")
    private String numeroCartao;
    @NotNull(message = "{password.not.null}")
    @NotEmpty(message = "{password.not.empty}")
    private String senhaCartao;
    @NotNull
    private Double valor;

    public Transaction mapToTransaction() {
        return new Transaction(numeroCartao, senhaCartao, valor);
    }
}
