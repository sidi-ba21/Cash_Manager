package com.cashmanager.cash.services.order;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cashmanager.cash.models.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

interface IOrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.client.id = :id")
    List<Order> findAllClientOrder(@Param("id") Long id);
}
