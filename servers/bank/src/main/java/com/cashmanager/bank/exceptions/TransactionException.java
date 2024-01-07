package com.cashmanager.bank.exceptions;

public class TransactionException extends Exception {

	public TransactionException() {
		super("");
	}

	public TransactionException(String message) {
		super(message);
	}

}
