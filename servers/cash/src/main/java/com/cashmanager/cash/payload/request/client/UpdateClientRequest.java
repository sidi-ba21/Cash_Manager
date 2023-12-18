package com.cashmanager.cash.payload.request.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateClientRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String password;

}