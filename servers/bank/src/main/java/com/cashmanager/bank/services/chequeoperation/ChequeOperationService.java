package com.cashmanager.bank.services.chequeoperation;

import com.cashmanager.bank.models.Admin;
import com.cashmanager.bank.models.BankAccount;
import com.cashmanager.bank.models.Cheque;
import com.cashmanager.bank.models.ChequeOperation;
import com.cashmanager.bank.payload.request.client.CreateTransactionRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
class ChequeOperationService implements IChequeOperationService {

    private IChequeOperationRepository chequeOperationRepository;

    @Override
    public ChequeOperation create(long amount, CreateTransactionRequest.Cheque chequeReq, BankAccount account, Admin admin) {
        ChequeOperation operation = new ChequeOperation(
                chequeReq.location(),
                chequeReq.issuedAt(),
                chequeReq.filedAt(),
                amount,
                account,
                admin
        );

        return this.chequeOperationRepository.save(operation);
    }

}
