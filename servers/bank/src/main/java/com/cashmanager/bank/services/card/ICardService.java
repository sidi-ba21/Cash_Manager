package com.cashmanager.bank.services.card;

import com.cashmanager.bank.exceptions.CardException;
import com.cashmanager.bank.models.Card;

public interface ICardService {

    Card find(String number, int month, int year, String cvv) throws CardException;

}
