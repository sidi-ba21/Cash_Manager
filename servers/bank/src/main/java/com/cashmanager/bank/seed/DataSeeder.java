package com.cashmanager.bank.seed;

import com.cashmanager.bank.models.BankAccount;
import com.cashmanager.bank.models.Client;
import com.cashmanager.bank.payload.request.client.AddClientRequest;
import com.cashmanager.bank.services.bankaccount.IBankAccountService;
import com.cashmanager.bank.services.client.IClientService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DataSeeder implements CommandLineRunner {

	private final IClientService clientService;
	private final IBankAccountService bankAccountService;

	@Override
	public void run(String... args) throws Exception {
		if (clientService.count() > 0) {
			return;
		}

		AddClientRequest addClientRequest = new AddClientRequest("John", "DOE", "john.doe@gmail.com", "password");
		Client client1 = this.clientService.add(addClientRequest);

		BankAccount bankAccount = this.bankAccountService.createSeed(client1);


	}

}
