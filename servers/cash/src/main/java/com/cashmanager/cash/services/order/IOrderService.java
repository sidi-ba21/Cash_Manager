package com.cashmanager.cash.services.order;

import com.cashmanager.cash.models.Article;
import com.cashmanager.cash.models.Client;
import com.cashmanager.cash.models.Order;
import com.cashmanager.cash.models.Payment;

import java.util.Optional;
import  java.util.List;

public interface IOrderService {

    Order add(List<Article> articles);

    Optional<Order> findById(Long id);

    List<Order> findAll();

    List<Order> findAllById(Long id);

    Order update(Long id, List<Article> articles);

    Order save(Order order);


    void delete(Long id);

    long count();

    Order setClient(Long id, Client client);
    
    Order validateOrder(Long id, Payment payment);
}
