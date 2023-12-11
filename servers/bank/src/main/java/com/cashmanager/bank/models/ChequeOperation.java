package com.cashmanager.bank.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "cheque_operations")
@Getter
@ToString
public class ChequeOperation {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private Date issuedAt;

        @Column(nullable = false)
        private Date filedAt;

        @Column(nullable = false)
        private Date inkedAt;

        @Column(nullable = false)
        private Long amount;

        @ManyToOne
        private BankAccount bankAccount;

}
