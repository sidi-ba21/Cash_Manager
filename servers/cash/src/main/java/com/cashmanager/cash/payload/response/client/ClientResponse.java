package com.cashmanager.cash.payload.response.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse {

    private long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;

}