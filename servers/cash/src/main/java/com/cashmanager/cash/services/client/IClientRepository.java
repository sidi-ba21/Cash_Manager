package com.cashmanager.cash.services.client;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cashmanager.cash.models.Client;

interface IClientRepository extends JpaRepository<Client, Long> {
}
