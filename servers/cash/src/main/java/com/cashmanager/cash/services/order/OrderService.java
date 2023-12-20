package com.cashmanager.cash.services.order;

import com.cashmanager.cash.models.Article;
import com.cashmanager.cash.models.Order;
import com.cashmanager.cash.models.Payment;
import com.cashmanager.cash.services.order.IOrderRepository;
import com.cashmanager.cash.services.payment.IPaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
class OrderService implements IOrderService {

    @Autowired
    private final IOrderRepository orderRepository;

    @Autowired
    private final IPaymentService paymentService;

    @Override
    public Order add(List<Article> articles) {
        // TODO : Validate data

        Long totalPrice = 0L;

        for (Article article : articles) {
            totalPrice += article.getPrice();
        }

        Order order = new Order(totalPrice);
        order.setArticles(articles);

        log.info("Saving new order {}.", order);

        return this.orderRepository.save(order);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order update(Long id, List<Article> articles) {
        Order order = orderRepository.findById(id).orElse(null);
        Long totalPrice = 0L;
        if (order == null) {
            return null;
        }

        for (Article article : articles) {
            totalPrice += article.getPrice();
        }
        order.setArticles(articles);
        order.setTotalPrice(totalPrice);
        return orderRepository.save(order);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public long count() {
        return this.orderRepository.count();
    }

    @Override
    public Order validateOrder(Long id, Payment payment) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            return null;
        }
        payment.setOrder(order);
        paymentService.save(payment);
        order.setPayment(payment);
        return orderRepository.save(order);
    }
}
