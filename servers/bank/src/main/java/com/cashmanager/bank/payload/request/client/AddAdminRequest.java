package com.cashmanager.bank.payload.request.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddAdminRequest {

    private String login;
    private String password;

}
