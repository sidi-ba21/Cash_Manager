package com.cashmanager.bank.services.client;

import com.cashmanager.bank.models.Client;
import com.cashmanager.bank.payload.request.client.AddClientRequest;

public interface IClientService {

	Client add(AddClientRequest data);

	long count();

}
