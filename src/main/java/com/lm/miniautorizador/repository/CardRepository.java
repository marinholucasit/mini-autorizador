package com.lm.miniautorizador.repository;

import com.lm.miniautorizador.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, String> {
}
