package com.cashmanager.bank.services.clientaccount;

import com.cashmanager.bank.models.ClientAccount;
import org.springframework.data.jpa.repository.JpaRepository;

interface IClientAccountRepository extends JpaRepository<ClientAccount, Long> {
}
