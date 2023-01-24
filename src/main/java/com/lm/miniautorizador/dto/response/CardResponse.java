package com.lm.miniautorizador.dto.response;

import com.lm.miniautorizador.entity.Card;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CardResponse {

    private String senha;
    private String numeroCartao;

    public CardResponse(Card card) {
        this.numeroCartao = card.getCardNumber();
        this.senha = card.getPasswordCard();
    }

}
