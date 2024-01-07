package com.cashmanager.bank.services.chequeoperation;

import com.cashmanager.bank.models.Admin;
import com.cashmanager.bank.models.BankAccount;
import com.cashmanager.bank.models.ChequeOperation;
import com.cashmanager.bank.payload.request.client.CreateTransactionRequest;

public interface IChequeOperationService {

    ChequeOperation create(long amount, CreateTransactionRequest.Cheque chequeReq, BankAccount account, Admin admin);

}
