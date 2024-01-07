package com.cashmanager.bank.services.card;

import com.cashmanager.bank.exceptions.CardException;
import com.cashmanager.bank.models.Card;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
class CardService implements ICardService {

    private ICardRepository cardRepository;

    @Override
    public Card find(String number, int month, int year, String cvv) throws CardException {
        Optional<Card> optionalCard = this.cardRepository.findByNumber(number);

        if (optionalCard.isEmpty()) {
            throw new CardException("Card not found");
        }

        Card card = optionalCard.get();

        YearMonth expAt = card.getExpiredAt();
        if (expAt.getMonth().getValue() != month || !String.valueOf(expAt.getYear()).substring(2).equals(String.valueOf(year))) {
            throw new CardException("Invalid expiration date");
        }

        if (!card.getCvv().equals(cvv)) {
            throw new CardException("Invalid cvv");
        }

        return card;
    }

}
