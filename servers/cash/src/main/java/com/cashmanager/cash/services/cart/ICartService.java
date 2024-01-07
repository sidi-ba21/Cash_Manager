package com.cashmanager.cash.services.cart;

import com.cashmanager.cash.models.Cart;
import com.cashmanager.cash.models.Client;
import com.cashmanager.cash.payload.request.cart.*;
import com.cashmanager.cash.models.Order;
import java.util.List;
import java.util.Optional;


public interface ICartService {

    Cart create();
    Cart add(Long id, AddItemCartRequest data);

    void delete(Long id, DeleteItemCartRequest data);

    Cart setClient(Long id, Cart cart);


    Optional<Cart> findById(Long id);

    List<Cart> findAll();

    //    Cart update(Long id, UpdateCartRequest data);

    Order validateCart(Long id, ValidateCartRequest data);

    void delete(Long id);

    Long count();

    Cart save(Cart cart);
}
