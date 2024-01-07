package com.cashmanager.bank.services.transaction;

import com.cashmanager.bank.exceptions.AdminException;
import com.cashmanager.bank.exceptions.CardException;
import com.cashmanager.bank.exceptions.ChequeException;
import com.cashmanager.bank.exceptions.TransactionException;
import com.cashmanager.bank.models.*;
import com.cashmanager.bank.models.enums.TransactionAction;
import com.cashmanager.bank.models.enums.TransactionType;
import com.cashmanager.bank.payload.request.client.CreateTransactionRequest;
import com.cashmanager.bank.services.admin.IAdminService;
import com.cashmanager.bank.services.bankaccount.IBankAccountService;
import com.cashmanager.bank.services.card.ICardService;
import com.cashmanager.bank.services.cheque.IChequeService;
import com.cashmanager.bank.services.chequeoperation.IChequeOperationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
class TransactionService implements ITransactionService {

	private ITransactionRepository transactionRepository;

	private ICardService cardService;
	private IChequeService chequeService;
	private IChequeOperationService chequeOperationService;

	private IAdminService adminService;

	private IBankAccountService bankAccountService;

	@Override
	public Transaction create(CreateTransactionRequest ctr) throws Exception {
		BankAccount account = this.check(ctr);

		Transaction transaction = new Transaction(
				ctr.getAmount(),
				ctr.getType(),
				ctr.getAction()
		);
		transaction.setBankAccount(account);

		if (ctr.getType().equals(TransactionType.CHEQUE)) {
			Admin existedAdmin = this.adminService.find(ctr.getAdmin().login(), ctr.getAdmin().password());
			this.chequeOperationService.create(ctr.getAmount(), ctr.getCheque(), account, existedAdmin);
		}

        if (ctr.getAction().equals(TransactionAction.RETRIEVE)) {
            this.bankAccountService.retrieve(account, ctr.getAmount());
        }

		return this.transactionRepository.save(transaction);
	}

	private BankAccount check(CreateTransactionRequest ctr) throws Exception {
		BankAccount account = switch (ctr.getType()) {
			case CARD -> this.checkCard(ctr.getCard());
            case CHEQUE -> this.checkCheque(ctr.getCheque(), ctr.getAdmin());
		};

		if (account == null) {
			throw new TransactionException("Invalid type");
		}

		switch (ctr.getAction()) {
			case RETRIEVE -> {
                this.checkRetrieve(account, ctr.getAmount());
			}
			default -> throw new TransactionException("Invalid action");
		}

		return account;
	}

	private BankAccount checkCard(CreateTransactionRequest.Card card) throws CardException {
		Card existedCard = this.cardService.find(card.number(), card.month(), card.year(), card.cvv());

		if (existedCard == null) {
			throw new CardException();
		}

		return existedCard.getBankAccount();
	}

	private BankAccount checkCheque(CreateTransactionRequest.Cheque cheque, CreateTransactionRequest.Admin admin) throws ChequeException, AdminException {
		Cheque existedCheque = this.chequeService.find(cheque.number(), cheque.month(), cheque.year());
		if (existedCheque == null) {
			throw new ChequeException();
		}

		Admin existedAdmin = this.adminService.find(admin.login(), admin.password());
		if (existedAdmin == null) {
			throw new AdminException();
		}

		return existedCheque.getBankAccount();
	}

	private void checkRetrieve(BankAccount account, long amount) throws TransactionException {
		if (this.bankAccountService.canRetrieve(account, amount)) {
			throw new TransactionException("Insufficient balance");
		}
	}

}
