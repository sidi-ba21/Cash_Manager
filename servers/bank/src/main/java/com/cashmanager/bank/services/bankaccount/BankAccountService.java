package com.cashmanager.bank.services.bankaccount;

import com.cashmanager.bank.models.BankAccount;
import com.cashmanager.bank.models.Card;
import com.cashmanager.bank.models.Cheque;
import com.cashmanager.bank.models.Client;
import com.cashmanager.bank.utils.UString;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.YearMonth;

@Service
@AllArgsConstructor
@Slf4j
class BankAccountService implements IBankAccountService {

	@PersistenceContext
	private EntityManager entityManager;

	private final IBankAccountRepository bankAccountRepository;

	@Transactional
	@Override
	public BankAccount create(Client client) {
		String accountNumber = UString.randomNumber(BankAccount.NUMBER_LENGTH);
		String cardNumber = UString.randomNumber(Card.NUMBER_LENGTH);
		String chequeNumber = UString.randomNumber(Cheque.NUMBER_LENGTH);

		client = entityManager.merge(client);

		BankAccount bankAccount = new BankAccount(accountNumber);
		bankAccount.setClient(client);

		Card card = new Card(cardNumber, UString.randomNumber(Card.CVV_LENGTH));
		card.setBankAccount(bankAccount);
		bankAccount.setCard(card);

		Cheque cheque = new Cheque(chequeNumber);
		cheque.setBankAccount(bankAccount);
		bankAccount.setCheque(cheque);

		log.info("Saving new bank account {}.", bankAccount);

		return this.bankAccountRepository.save(bankAccount);
	}

	@Transactional
	@Override
	public BankAccount createSeed(Client client) {
		String accountNumber = "0123456789";
		String cardNumber = "0123456789012345";
		String cardCVV = "123";
		String chequeNumber = "0123456";
		int year = 2024;
		int month = 12;
		long balance = 100;

		client = entityManager.merge(client);

		BankAccount bankAccount = new BankAccount(accountNumber);
		bankAccount.setBalance(balance);
		bankAccount.setClient(client);

		Card card = new Card(cardNumber, cardCVV);
		card.setExpiredAt(YearMonth.of(year, month));
		card.setBankAccount(bankAccount);
		bankAccount.setCard(card);

		Cheque cheque = new Cheque(chequeNumber);
		cheque.setExpiredAt(YearMonth.of(year, month));
		cheque.setBankAccount(bankAccount);
		bankAccount.setCheque(cheque);

		log.info("Saving new bank account {}.", bankAccount);

		return this.bankAccountRepository.save(bankAccount);
	}

	@Override
	public void retrieve(BankAccount account, long amount) {
		account.setBalance(account.getBalance() - amount);
		this.bankAccountRepository.save(account);
	}

	@Override
	public boolean canRetrieve(BankAccount account, long amount) {
		return account.getBalance() < amount;
	}


}
