package com.lm.miniautorizador.dto.request;

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
    @NotEmpty
    private Double valor;
}
