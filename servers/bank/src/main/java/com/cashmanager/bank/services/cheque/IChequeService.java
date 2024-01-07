package com.cashmanager.bank.services.cheque;

import com.cashmanager.bank.exceptions.ChequeException;
import com.cashmanager.bank.models.Cheque;

public interface IChequeService {

    Cheque find(String number, int month, int year) throws ChequeException;

}
