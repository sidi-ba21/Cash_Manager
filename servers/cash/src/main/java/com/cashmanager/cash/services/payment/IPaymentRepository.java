package com.cashmanager.cash.services.payment;

import com.cashmanager.cash.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

interface IPaymentRepository extends JpaRepository<Payment, Long> {
}
