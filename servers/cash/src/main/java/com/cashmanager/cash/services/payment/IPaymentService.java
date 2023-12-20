package com.cashmanager.cash.services.payment;

import com.cashmanager.cash.models.Payment;
import java.util.Optional;
public interface IPaymentService {

    Payment add(String typePayment);

    Optional<Payment> findById(Long id);

    Payment update(Long id, String typePayment);

    void delete(Long id);

    long count();

    Payment save(Payment payment);
}
