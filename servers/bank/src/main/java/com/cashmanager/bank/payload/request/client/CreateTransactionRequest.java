package com.cashmanager.bank.payload.request.client;

import com.cashmanager.bank.models.enums.TransactionAction;
import com.cashmanager.bank.models.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateTransactionRequest {

	private Long amount;
	private TransactionType type;
	private TransactionAction action;

	private Card card;
	private Cheque cheque;

	private Admin admin;

	public record Card(String number, int month, int year, String cvv) {
	}

	public record Cheque(
			String number,
			int month,
			int year,
			String location,
			@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime issuedAt,
			@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime filedAt
	) {
	}

	public record Admin(String login, String password) {
	}

}
