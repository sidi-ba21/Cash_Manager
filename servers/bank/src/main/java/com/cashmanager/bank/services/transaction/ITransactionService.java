package com.cashmanager.bank.services.transaction;

import com.cashmanager.bank.exceptions.TransactionException;
import com.cashmanager.bank.models.Transaction;
import com.cashmanager.bank.payload.request.client.CreateTransactionRequest;

public interface ITransactionService {

	Transaction create(CreateTransactionRequest ctr) throws TransactionException;

}
