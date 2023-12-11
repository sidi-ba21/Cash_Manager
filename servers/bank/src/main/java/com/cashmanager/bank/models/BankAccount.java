package com.cashmanager.bank.models;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@ToString
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

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToOne
	private Card card;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToOne
	private Cheque cheque;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToOne(mappedBy = "bankAccount")
	private Client client;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToMany(mappedBy = "bankAccount")
	private List<Transaction> transactions = new ArrayList<>();

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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
