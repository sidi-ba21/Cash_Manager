package com.cashmanager.cash.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;
import java.util.ArrayList;


@Entity
@Table(name = "clients")
@Getter
@Setter
@ToString
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

//	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToOne
	private ClientAccount clientAccount;

//	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToOne
	private Cart cart;

	//@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToMany(mappedBy = "client")
	private List<Order> orders = new ArrayList<>();

	public Client() {
	}

	public Client(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

}
