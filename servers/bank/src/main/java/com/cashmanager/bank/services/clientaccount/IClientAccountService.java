package com.cashmanager.bank.services.clientaccount;

import com.cashmanager.bank.models.ClientAccount;
import com.cashmanager.bank.payload.request.client.AddClientRequest;

public interface IClientAccountService {

	ClientAccount add(AddClientRequest data);

	long count();

}
