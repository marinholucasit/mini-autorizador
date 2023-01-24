package com.lm.miniautorizador.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Id
    private String cardNumber;

    @NotNull
    private String passwordCard;

    @NotNull
    private Double balance;

    public Card(String cardNumber, String passwordCard) {
        this.cardNumber = cardNumber;
        this.passwordCard = passwordCard;
        this.balance =  500.00;
    }

}
