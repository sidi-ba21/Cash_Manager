package com.cashmanager.bank.services.card;

import com.cashmanager.bank.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

interface ICardRepository extends JpaRepository<Card, Long> {

    @Query("SELECT c FROM Card c WHERE c.number = :number")
    Optional<Card> findByNumber(@Param("number") String number);

}
