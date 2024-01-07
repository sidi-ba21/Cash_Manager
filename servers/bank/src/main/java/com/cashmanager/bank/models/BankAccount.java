package com.cashmanager.bank.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bank_accounts")
@Getter
@Setter
public class BankAccount {

	public static final int NUMBER_LENGTH = 10;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	@Size(min = NUMBER_LENGTH, max = NUMBER_LENGTH)
	private String number;

	@Column
	private Long balance = 0L;

	@Column(updatable = false)
	private LocalDateTime createdAt;

	@Column
	private LocalDateTime updatedAt;

	@OneToOne(cascade = CascadeType.ALL)
	private Card card;

	@OneToOne(cascade = CascadeType.ALL)
	private Cheque cheque;

	@OneToOne(cascade = CascadeType.ALL)
	private Client client;

	@OneToMany(mappedBy = "bankAccount")
	private List<Transaction> transactions = new ArrayList<>();

	@OneToMany(mappedBy = "bankAccount")
	private List<ChequeOperation> chequeOperations = new ArrayList<>();

	public BankAccount() {
	}

	public BankAccount(String number) {
		this.number = number;
		this.balance = 0L;

		LocalDateTime now = LocalDateTime.now();
		this.createdAt = now;
		this.updatedAt = now;
	}

}
