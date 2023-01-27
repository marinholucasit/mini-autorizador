package com.lm.miniautorizador.dto.request;

import com.lm.miniautorizador.entity.Card;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardRequest {

    @NotNull
    @NotEmpty
    @Digits(integer = 100, fraction = 0, message = "{cardNumber.only.number}")
    private String numeroCartao;

    @NotNull(message = "{password.not.null}")
    @NotEmpty(message = "{password.not.empty}")
    private String senha;

    public Card mapToCard() {
        return new Card(numeroCartao, senha);
    }
}
