package com.cashmanager.bank.services.clientaccount;

import com.cashmanager.bank.models.Client;
import com.cashmanager.bank.models.ClientAccount;
import com.cashmanager.bank.payload.request.client.AddClientRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
class ClientAccountService implements IClientAccountService {

	private final IClientAccountRepository clientAccountRepository;

	@Override
	public ClientAccount add(AddClientRequest data) {
		// TODO : Validate data

		ClientAccount account = new ClientAccount(data.getEmail(), data.getPassword());
		Client client = new Client(data.getFirstname(), data.getLastname());

		account.setClient(client);

		log.info("Saving new client account {}.", account);

		return this.clientAccountRepository.save(account);
	}

	@Override
	public long count() {
		return this.clientAccountRepository.count();
	}
}
