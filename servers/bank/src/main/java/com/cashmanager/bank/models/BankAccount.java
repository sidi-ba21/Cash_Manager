package com.cashmanager.bank.models;

import jakarta.persistence.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "bank_accounts", uniqueConstraints = {
		@UniqueConstraint(columnNames = "number")
})
@Getter
@Setter
public class BankAccount implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String number;

	@Column(nullable = false)
	private Long balance;

	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(nullable = false)
	private LocalDateTime updatedAt;

	@JsonIgnore
	@OneToOne(mappedBy = "bankAccount")
	private Client client;

	@JsonIgnore
	@OneToMany(mappedBy = "bankAccount")
	private Collection<Transaction> transactions;

	@JsonIgnore
	@OneToOne
	private Card card;

	@Override
	public String toString() {
		return "BankAccount{" +
				"id=" + id +
				", number='" + number + '\'' +
				", balance='" + balance + '\'' +
				", createdAt=" + createdAt +
				", updatedAt=" + updatedAt +
				'}';
	}

	public BankAccount() {
		super();
	}

	public BankAccount(String number, Long balance) {
		super();
		this.number = number;
		this.balance = balance;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}
}
