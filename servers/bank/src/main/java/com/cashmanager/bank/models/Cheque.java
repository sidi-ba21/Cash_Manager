package com.cashmanager.bank.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.YearMonth;

@Entity
@Table(name = "cheques")
@Getter
@Setter
@ToString
public class Cheque {

    public static final int NUMBER_LENGTH = 7;
    public static final int EXPIRED_AT_YEARS = 3;

    @Id
    @Column(nullable = false, unique = true)
    @Size(min = 7, max = 7)
    private String number;

    @Column(nullable = false, updatable = false)
    private YearMonth expiredAt;

    @OneToOne
    private BankAccount bankAccount;

    public Cheque() {
    }

    public Cheque(String number) {
        this.number = number;
        this.expiredAt = YearMonth.now().plusYears(EXPIRED_AT_YEARS);
    }

}
