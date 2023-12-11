package com.cashmanager.bank.services.client;

import com.cashmanager.bank.models.Client;
import com.cashmanager.bank.models.ClientAccount;
import com.cashmanager.bank.payload.request.client.AddClientRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
class ClientService implements IClientService {

	private final IClientRepository clientRepository;

	@Override
	public Client add(AddClientRequest data) {
		// TODO : Validate data

		Client client = new Client(data.getFirstname(), data.getLastname());
		ClientAccount account = new ClientAccount(data.getEmail(), data.getPassword());

		client.setClientAccount(account);

		log.info("Saving new client {}.", client);

		return this.clientRepository.save(client);
	}

	@Override
	public long count() {
		return this.clientRepository.count();
	}
}
