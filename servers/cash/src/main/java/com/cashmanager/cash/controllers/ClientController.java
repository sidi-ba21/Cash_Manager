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

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/v1/clients")
@AllArgsConstructor
@Slf4j
public class ClientController {
    private final IClientAccountService clientAccountService;
    private final ICartService cartService;

    @PostMapping
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
    
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> get(@PathVariable Long id) {
        log.info("get client by id : " + id);
        ClientResponse response = new ClientResponse();

        try {
            ClientAccount clientAccount = clientAccountService.findById(id).orElse(null);
            if (clientAccount == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            responseBody(response, clientAccount);

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponse> update(@PathVariable Long id, @RequestBody UpdateClientRequest data) {
        ClientResponse response = new ClientResponse();

        try {
            ClientAccount clientAccount = clientAccountService.update(id, data);
            if (clientAccount == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            responseBody(response, clientAccount);

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClientResponse> delete(@PathVariable Long id) {
        ClientResponse response = new ClientResponse();

        try {
            clientAccountService.delete(id);

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ClientResponse>> getAll() {
        // i want to return a list of all clients
        List<ClientResponse> response = new ArrayList<>();

        try {
            List<ClientAccount> clientAccounts = clientAccountService.findAll();
            if (clientAccounts == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            for (ClientAccount clientAccount : clientAccounts) {
                ClientResponse clientResponse = new ClientResponse();
                responseBody(clientResponse, clientAccount);
                response.add(clientResponse);
            }

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
