package com.cashmanager.cash.services.clientaccount;

import com.cashmanager.cash.models.ClientAccount;
import org.springframework.data.jpa.repository.JpaRepository;

interface IClientAccountRepository extends JpaRepository<ClientAccount, Long> {
}
