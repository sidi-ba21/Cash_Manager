package com.cashmanager.bank.services.bankaccount;

import com.cashmanager.bank.models.BankAccount;
import com.cashmanager.bank.models.Client;

public interface IBankAccountService {

	BankAccount create(Client client);
	BankAccount createSeed(Client client);

}
