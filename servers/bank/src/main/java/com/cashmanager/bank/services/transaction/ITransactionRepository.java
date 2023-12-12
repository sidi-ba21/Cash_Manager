package com.cashmanager.bank.services.transaction;

import com.cashmanager.bank.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

interface ITransactionRepository extends JpaRepository<Transaction, Long> {
}
