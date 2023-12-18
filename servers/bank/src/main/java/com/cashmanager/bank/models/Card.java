package com.cashmanager.bank.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.YearMonth;

@Entity
@Table(name = "cards")
@Getter
@Setter
@ToString
public class Card {

    public static final int NUMBER_LENGTH = 16;
    public static final int CVV_LENGTH = 3;
    public static final int EXPIRED_AT_YEARS = 3;

    @Id
    @Column(unique = true)
    @Size(min = NUMBER_LENGTH, max = NUMBER_LENGTH)
    private String number;

    @Column
    private YearMonth expiredAt;

    @Column(nullable = false)
    @Size(min = CVV_LENGTH, max = CVV_LENGTH)
    private String cvv;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @OneToOne
    private BankAccount bankAccount;

    public Card() {
    }

    public Card(String number, String cvv) {
        this.number = number;
        this.cvv = cvv;
        this.expiredAt = YearMonth.now().plusYears(EXPIRED_AT_YEARS);

        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

}
