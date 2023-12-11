package com.cashmanager.bank.seed;

import com.cashmanager.bank.models.BankAccount;
import com.cashmanager.bank.models.Client;
import com.cashmanager.bank.models.ClientAccount;
import com.cashmanager.bank.payload.request.client.AddClientRequest;
import com.cashmanager.bank.services.bankaccount.IBankAccountService;
import com.cashmanager.bank.services.clientaccount.IClientAccountService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DataSeeder implements CommandLineRunner {

	private final IClientAccountService clientAccountService;
	private final IBankAccountService bankAccountService;

	@Override
	public void run(String... args) throws Exception {
		if (clientAccountService.count() > 0) {
			return;
		}

		this.createClient1();
	}

	private void createClient1() {
		String firstname = "John";
		String lastname = "DOE";
		String email = "john.doe@gmail.com";
		String password = "password";

		this.createClient(firstname, lastname, email, password);
	}


	private void createClient(String firstname, String lastname, String email, String password) {
		AddClientRequest addClientRequest = new AddClientRequest(firstname, lastname, email, password);
		ClientAccount clientAccount1 = this.clientAccountService.add(addClientRequest);

		//BankAccount bankAccount = this.bankAccountService.createSeed(clientAccount1.getClient());
	}

}
