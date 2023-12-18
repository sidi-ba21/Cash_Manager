package com.cashmanager.cash.services.clientaccount;

import com.cashmanager.cash.models.Client;
import com.cashmanager.cash.models.ClientAccount;
import com.cashmanager.cash.payload.request.client.AddClientRequest;
import com.cashmanager.cash.payload.request.client.UpdateClientRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
class ClientAccountService implements IClientAccountService {

	@Autowired
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

        if (existingAccount.isPresent()) {
            ClientAccount clientAccount = existingAccount.get();
            // Mettre à jour les champs requis
            if (data.getEmail() != null) {
                clientAccount.setEmail(data.getEmail());
            }
            if (data.getPassword() != null) {
                clientAccount.setPassword(data.getPassword());
            }
			if (data.getFirstname() != null) {
				clientAccount.getClient().setFirstname(data.getFirstname());
			}
			if (data.getLastname() != null) {
				clientAccount.getClient().setLastname(data.getLastname());
			}

            return clientAccountRepository.save(clientAccount);  // Sauvegarder les modifications
        } else {
			return null;
        }

	@Override
	public long count() {
		return this.clientAccountRepository.count();
	}
}
