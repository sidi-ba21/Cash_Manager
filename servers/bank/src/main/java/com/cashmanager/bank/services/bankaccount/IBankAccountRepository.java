package com.cashmanager.bank.services.bankaccount;

import com.cashmanager.bank.models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

interface IBankAccountRepository extends JpaRepository<BankAccount, Long> {



}
