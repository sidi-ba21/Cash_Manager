package com.cashmanager.bank.models;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bank_accounts")
@Getter
@Setter
@ToString
public class BankAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String number;

	@Column
	private Long balance;

	@Column(updatable = false)
	private Date createdAt;

	@Column
	private Date updatedAt;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToOne(mappedBy = "bankAccount")
	private Client client;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToMany(mappedBy = "bankAccount")
	private List<Transaction> transactions = new ArrayList<>();

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToOne
	private Card card;

	public BankAccount() {
	}

	public BankAccount(String number, Long balance) {
		this.number = number;
		this.balance = balance;
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}

}
