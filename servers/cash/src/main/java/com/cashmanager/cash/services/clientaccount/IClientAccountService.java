package com.cashmanager.cash.services.clientaccount;

import com.cashmanager.cash.models.ClientAccount;
import com.cashmanager.cash.payload.request.client.AddClientRequest;
import com.cashmanager.cash.payload.request.client.UpdateClientRequest;
import java.util.List;
import java.util.Optional;

public interface IClientAccountService {

	ClientAccount add(AddClientRequest data);
    Optional<ClientAccount> findById(Long id);
    List<ClientAccount> findAll();
    ClientAccount update(Long id, UpdateClientRequest data);
    void delete(Long id);
    long count();

    ClientAccount save(ClientAccount clientAccount);

}
