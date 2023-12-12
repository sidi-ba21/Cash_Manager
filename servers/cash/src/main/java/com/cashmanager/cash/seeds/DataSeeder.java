package com.cashmanager.cash.seeds;

import com.cashmanager.cash.models.Client;
import com.cashmanager.cash.payload.request.client.AddClientRequest;
import com.cashmanager.cash.services.client.IClientService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DataSeeder implements CommandLineRunner {

	private final IClientService clientService;

	@Override
	public void run(String... args) throws Exception {
		if (clientService.count() > 0) {
			return;
		}

		AddClientRequest addClientRequest = new AddClientRequest("John", "DOE", "john.doe@gmail.com", "password");
		Client client1 = this.clientService.add(addClientRequest);


	}

}
