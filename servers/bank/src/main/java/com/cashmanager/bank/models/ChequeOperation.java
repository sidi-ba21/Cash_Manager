package com.cashmanager.bank.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "cheque_operations")
@Getter
public class ChequeOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private LocalDateTime issuedAt;

    @Column(nullable = false)
    private LocalDateTime filedAt;

    @Column(nullable = false)
    private LocalDateTime inkedAt;

    @Column(nullable = false)
    private Long amount;

    @ManyToOne
    private BankAccount bankAccount;

    @OneToOne
    private Admin admin;

    public ChequeOperation() {
    }

    public ChequeOperation(String location,
                           LocalDateTime issuedAt,
                           LocalDateTime filedAt,
                           Long amount,
                           BankAccount account,
                           Admin admin) {

        this.location = location;
        this.issuedAt = issuedAt;
        this.filedAt = filedAt;
        this.inkedAt = LocalDateTime.now();
        this.amount = amount;
        this.bankAccount = account;
        this.admin = admin;
    }

}
