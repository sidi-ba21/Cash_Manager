package com.cashmanager.bank.services.bankaccount;

import com.cashmanager.bank.models.BankAccount;
import com.cashmanager.bank.models.Card;
import com.cashmanager.bank.models.Cheque;
import com.cashmanager.bank.models.Client;
import com.cashmanager.bank.utils.UString;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
class BankAccountService implements IBankAccountService {

	private final IBankAccountRepository bankAccountRepository;

	@Override
	public BankAccount create(Client client) {
		String accountNumber = UString.randomNumber(BankAccount.NUMBER_LENGTH);
		String cardNumber = UString.randomNumber(Card.NUMBER_LENGTH);
		String chequeNumber = UString.randomNumber(Cheque.NUMBER_LENGTH);

		BankAccount bankAccount = new BankAccount(accountNumber);

		Card card = new Card(cardNumber, UString.randomNumber(Card.CVV_LENGTH));
		bankAccount.setCard(card);

		Cheque cheque = new Cheque(chequeNumber);
		bankAccount.setCheque(cheque);

		bankAccount.setClient(client);

		log.info("Saving new bank account {}.", bankAccount);

		return this.bankAccountRepository.save(bankAccount);
	}

	@Override
	public BankAccount createSeed(Client client) {
		String accountNumber = "0123456789";
		String cardNumber = "0123456789012345";
		String cardCVV = "123";
		String chequeNumber = "0123456";

		BankAccount bankAccount = new BankAccount(accountNumber);

		Card card = new Card(cardNumber, cardCVV);
		bankAccount.setCard(card);

		Cheque cheque = new Cheque(chequeNumber);
		bankAccount.setCheque(cheque);

		bankAccount.setClient(client);

		log.info("Saving new bank account {}.", bankAccount);

		return this.bankAccountRepository.save(bankAccount);
	}


}
