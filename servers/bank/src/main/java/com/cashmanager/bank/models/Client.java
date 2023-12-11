package com.cashmanager.bank.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "clients")
@Getter
@Setter
@ToString
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String firstName;

	@Column
	private String lastName;

	@OneToOne
	private ClientAccount clientAccount;

	@OneToOne
	private BankAccount bankAccount;

	public Client() {
	}

	public Client(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

}
