package com.cashmanager.bank.services.cheque;

import com.cashmanager.bank.models.Cheque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

interface IChequeRepository extends JpaRepository<Cheque, Long> {

    @Query("SELECT c FROM Cheque c WHERE c.number = :number")
    Optional<Cheque> findByNumber(@Param("number") String number);

}
