package com.cashmanager.cash.services.cart;

import com.cashmanager.cash.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

interface ICartRepository extends JpaRepository<Cart, Long> {
}