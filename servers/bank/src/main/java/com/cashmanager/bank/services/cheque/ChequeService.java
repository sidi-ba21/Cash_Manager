package com.cashmanager.bank.services.cheque;

import com.cashmanager.bank.exceptions.ChequeException;
import com.cashmanager.bank.models.Cheque;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
class ChequeService implements IChequeService {

    private IChequeRepository chequeRepository;

    @Override
    public Cheque find(String number, int month, int year) throws ChequeException {
        Optional<Cheque> optionalCheque = this.chequeRepository.findByNumber(number);

        if (optionalCheque.isEmpty()) {
            throw new ChequeException("Cheque not found");
        }

        Cheque cheque = optionalCheque.get();

        YearMonth expAt = cheque.getExpiredAt();
        if (expAt.getMonth().getValue() != month || !String.valueOf(expAt.getYear()).substring(2).equals(String.valueOf(year))) {
            throw new ChequeException("Invalid expiration date");
        }

        return cheque;
    }

}
