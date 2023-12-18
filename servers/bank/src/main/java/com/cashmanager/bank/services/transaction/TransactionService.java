package com.cashmanager.bank.services.transaction;

import com.cashmanager.bank.exceptions.TransactionException;
import com.cashmanager.bank.models.Transaction;
import com.cashmanager.bank.payload.request.client.CreateTransactionRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
class TransactionService implements ITransactionService {

	private ITransactionRepository transactionRepository;

	@Override
	public Transaction create(CreateTransactionRequest ctr) throws TransactionException {
		if (!this.canBeProcess()) {
			throw new TransactionException("");
		}



		return null;
	}

	private boolean canBeProcess() {
		return false;
	}

}
