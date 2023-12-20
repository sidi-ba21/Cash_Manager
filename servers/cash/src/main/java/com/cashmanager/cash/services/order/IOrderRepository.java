package com.cashmanager.cash.services.order;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cashmanager.cash.models.Order;

interface IOrderRepository extends JpaRepository<Order, Long> {

}
