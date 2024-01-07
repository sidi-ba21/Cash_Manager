package com.cashmanager.bank.services.admin;

import com.cashmanager.bank.exceptions.AdminException;
import com.cashmanager.bank.models.Admin;
import com.cashmanager.bank.payload.request.client.AddAdminRequest;

public interface IAdminService {

    Admin create(AddAdminRequest request);

    Admin find(String login, String password) throws AdminException;

}
