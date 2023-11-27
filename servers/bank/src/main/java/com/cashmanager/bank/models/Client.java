package com.cashmanager.bank.models;

import com.fasterxml.jackson.annotation.JsonProperty;
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

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToOne(cascade = CascadeType.ALL)
	private ClientAccount clientAccount;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToOne(cascade = CascadeType.ALL)
	private BankAccount bankAccount;

	public Client() {
	}

	public Client(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

}
