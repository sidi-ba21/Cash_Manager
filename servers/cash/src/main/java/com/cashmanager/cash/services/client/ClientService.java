package com.cashmanager.cash.services.client;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.cashmanager.cash.services.client.IClientRepository;
import com.cashmanager.cash.models.Client;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
class ClientService implements IClientService {

    private final IClientRepository clientRepository;

    @Override
    public Client add(String firstname, String lastname) {
        // TODO : Validate data

        Client client = new Client(firstname, lastname);

     //   log.info("Saving new client {}.", client);

        return this.clientRepository.save(client);
    }

    @Override
    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Client update(Long id, String firstname, String lastname) {
        Client client = clientRepository.findById(id).get();
        client.setFirstName(firstname);
        client.setLastName(lastname);
        return clientRepository.save(client);
    }

    @Override
    public long count() {
        return this.clientRepository.count();
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }
}
