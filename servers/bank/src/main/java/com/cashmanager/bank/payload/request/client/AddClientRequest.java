package com.cashmanager.bank.payload.request.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddClientRequest {

	private String firstname;
	private String lastname;
	private String email;
	private String password;

}
