package com.lm.miniautorizador.model;

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
public class Transaction {

    @NotNull
    @NotEmpty
    private String cardNumber;
    @NotNull
    @NotEmpty
    private String cardPassword;
    @NotNull
    @NotEmpty
    private Double transactionAmount;

}
