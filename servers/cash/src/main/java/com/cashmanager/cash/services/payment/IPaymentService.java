package com.cashmanager.cash.services.payment;

import com.cashmanager.cash.models.Order;
import com.cashmanager.cash.models.Payment;
import com.cashmanager.cash.payload.request.cart.ValidateCartRequest;

import java.util.Optional;
public interface IPaymentService {

    Payment add(ValidateCartRequest data);


    void setOrder(Long id, Order order);

    Optional<Payment> findById(Long id);

    Payment update(Long id, ValidateCartRequest data);

    void delete(Long id);

    long count();

    Payment save(Payment payment);
}
