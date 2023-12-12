package com.cashmanager.cash.services.clientaccount;

import com.cashmanager.cash.models.ClientAccount;
import com.cashmanager.cash.payload.request.client.AddClientRequest;

public interface IClientAccountService {

	ClientAccount add(AddClientRequest data);

	long count();

}
