package com.cashmanager.bank.services.client;

import com.cashmanager.bank.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

interface IClientRepository extends JpaRepository<Client, Long> {
}
