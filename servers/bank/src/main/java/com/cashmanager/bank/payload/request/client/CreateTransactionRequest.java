package com.cashmanager.bank.payload.request.client;

import com.cashmanager.bank.models.enums.TransactionAction;
import com.cashmanager.bank.models.enums.TransactionType;
import lombok.Data;

@Data
public class CreateTransactionRequest {

	private Long amount;
	private TransactionType type;
	private TransactionAction action;

	private Card card;

	public record Card(String number, int month, int year, String cvv) {
	}

}
