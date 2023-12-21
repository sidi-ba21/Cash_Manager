package com.cashmanager.cash.services.payment;

import com.cashmanager.cash.models.Order;
import com.cashmanager.cash.models.Payment;
import com.cashmanager.cash.payload.request.cart.ValidateCartRequest;
import com.cashmanager.cash.services.payment.IPaymentService;
import com.cashmanager.cash.services.payment.IPaymentRepository;
import com.cashmanager.cash.models.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
class PaymentService implements IPaymentService {

    @Autowired
    private final IPaymentRepository paymentRepository;

    @Override
    public Payment add(ValidateCartRequest data) {
        // TODO : Validate data
        TransactionType type = TransactionType.valueOf(data.getTypePayment());
        Boolean allowed = data.getAllowed();

        Payment payment = new Payment(type, allowed);

//        log.info("Saving new payment {}.", payment);

        return this.paymentRepository.save(payment);
    }

    @Override
    public void setOrder(Long id, Order order) {
        Payment payment = paymentRepository.findById(id).orElse(null);
        if (payment == null) {
            return;
        }
        payment.setOrder(order);
        paymentRepository.save(payment);
    }

    @Override
    public Optional<Payment> findById(Long id) {
        return paymentRepository.findById(id);
    }

    @Override
    public Payment update(Long id, ValidateCartRequest data) {
        Payment payment = paymentRepository.findById(id).orElse(null);

        if (payment == null) {
            return null;
        }
        if (data.getAllowed() != null) {
            payment.setAllowed(data.getAllowed());
        }
        if (data.getTypePayment() != null) {
            payment.setType(TransactionType.valueOf(data.getTypePayment()));
        }

        //    log.info("Updating payment {}.", payment);
        return paymentRepository.save(payment);
    }

    @Override
    public void delete(Long id) {
        Payment payment = paymentRepository.findById(id).orElse(null);
        if (payment == null) {
            return;
        }
      //  log.info("Deleting payment {}.", payment);
        paymentRepository.delete(payment);
    }

    @Override
    public long count() {
        return paymentRepository.count();
    }

    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }
}
