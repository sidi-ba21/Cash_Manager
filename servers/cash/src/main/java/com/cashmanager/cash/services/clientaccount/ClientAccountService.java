package com.cashmanager.cash.services.clientaccount;

import com.cashmanager.cash.models.Cart;
import com.cashmanager.cash.models.Client;
import com.cashmanager.cash.models.ClientAccount;
import com.cashmanager.cash.services.cart.ICartService;
import com.cashmanager.cash.services.client.IClientService;
import com.cashmanager.cash.services.clientaccount.IClientAccountRepository;
import com.cashmanager.cash.payload.request.client.AddClientRequest;
import com.cashmanager.cash.payload.request.client.UpdateClientRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
class ClientAccountService implements IClientAccountService {

	private final IClientAccountRepository clientAccountRepository;

	private final IClientService clientService;


	@Override
	public ClientAccount add(AddClientRequest data) {
		// TODO : Validate data


		Client client = clientService.add(data.getFirstname(), data.getLastname());

		ClientAccount account = new ClientAccount(data.getEmail(), data.getPassword());


		// set client account
		client.setClientAccount(account);
		account.setClient(client);

		// save
		ClientAccount savedAccount = this.clientAccountRepository.save(account);
		clientService.save(client);

	//	log.info("Saving new client account {}.", savedAccount);

		return savedAccount;
	}

	@Override
	public void setCart(Long id, Cart cart) {
		ClientAccount clientAccount = clientAccountRepository.findById(id).orElse(null);
		if (clientAccount == null) {
			return;
		}
		Client client = clientAccount.getClient();
		clientService.setCart(client.getId(), cart);
		clientAccount.setClient(client);
	}

	@Override
    public Optional<ClientAccount> findById(Long id) {
        return clientAccountRepository.findById(id);
    }

    @Override
    public List<ClientAccount> findAll() {
        return clientAccountRepository.findAll();
    }

	 @Override
    public ClientAccount update(Long id, UpdateClientRequest data) {
        Optional<ClientAccount> existingAccount = clientAccountRepository.findById(id);

        if (!existingAccount.isPresent()) {
		 return null;
		}

        ClientAccount clientAccount = existingAccount.get();
		Client client = clientAccount.getClient();

        if (data.getEmail() != null) {
            clientAccount.setEmail(data.getEmail());
        }

        if (data.getPassword() != null) {
            clientAccount.setPassword(data.getPassword());
        }

		if (data.getFirstname() != null) {
			client.setFirstName(data.getFirstname());
		}

		if (data.getLastname() != null) {
			client.setLastName(data.getLastname());
		}

        return clientAccountRepository.save(clientAccount);
    }

	@Override
	public void delete(Long id) {
		Optional<ClientAccount> existingAccount = clientAccountRepository.findById(id);

		if (!existingAccount.isPresent()) {
			return;
		}

		ClientAccount clientAccount = existingAccount.get();

		clientAccountRepository.delete(clientAccount);
	}

	@Override
	public long count() {
		return this.clientAccountRepository.count();
	}

	@Override
	public ClientAccount save(ClientAccount clientAccount) {
		this.clientService.save(clientAccount.getClient());
		return this.clientAccountRepository.save(clientAccount);
	}
}
