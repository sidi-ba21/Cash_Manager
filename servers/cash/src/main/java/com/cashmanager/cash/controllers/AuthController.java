package com.cashmanager.cash.controllers;

import com.cashmanager.cash.models.ClientAccount;
import com.cashmanager.cash.models.Cart;
import com.cashmanager.cash.payload.request.client.*;
import com.cashmanager.cash.payload.response.client.ClientResponse;
import com.cashmanager.cash.services.clientaccount.IClientAccountService;
import com.cashmanager.cash.services.cart.ICartService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {
    private  final IClientAccountService clientAccountService;
    private final ICartService cartService;

    @PostMapping("/register")
    public ResponseEntity<ClientResponse> add(@RequestBody AddClientRequest data) {
        ClientResponse response = new ClientResponse();

        try {
            ClientAccount clientAccount = clientAccountService.add(data);
            Cart cart = cartService.create();
            cartService.setClient(clientAccount.getId(), cart);
            clientAccountService.setCart(clientAccount.getId(), cart);
            responseBody(response, clientAccount);

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ClientResponse> login(@RequestBody LoginRequest data) {
        ClientResponse response = new ClientResponse();

        try {
            ClientAccount clientAccount = clientAccountService.login(data);
            if (clientAccount == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            responseBody(response, clientAccount);

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    private void responseBody(ClientResponse response, ClientAccount clientAccount) {
        response.setId(clientAccount.getId());
        response.setFirstname(clientAccount.getClient().getFirstName());
        response.setLastname(clientAccount.getClient().getLastName());
        response.setEmail(clientAccount.getEmail());
        response.setPassword(clientAccount.getPassword());
        response.setCreatedAt(clientAccount.getCreatedAt());
        response.setUpdatedAt(clientAccount.getUpdatedAt());
    }


}
