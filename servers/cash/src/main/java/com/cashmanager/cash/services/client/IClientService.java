package com.cashmanager.cash.services.client;

import com.cashmanager.cash.models.Cart;
import com.cashmanager.cash.models.Client;
import java.util.Optional;

public interface IClientService {

        Client add(String firstName, String lastName);

        Client setCart(Long id, Cart cart);


        void delete(Long id);

        Client update(Long id, String firstName, String lastName);

        Optional<Client> findById(Long id);

        long count();

        Client save(Client client);
}
