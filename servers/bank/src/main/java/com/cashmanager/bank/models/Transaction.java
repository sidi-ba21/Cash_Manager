package com.cashmanager.bank.models;

import com.cashmanager.bank.models.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "transactions")
@Getter
@ToString
public class Transaction {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private Long amount;

        @Column(nullable = false)
        @Enumerated(EnumType.STRING)
        private TransactionType type;

        @Column(nullable = false, updatable = false)
        private Date createdAt;

        @Column(nullable = false)
        private Date updatedAt;

        @ManyToOne
        private BankAccount bankAccount;

        public Transaction() {
        }

        public Transaction(Long amount, TransactionType type) {
            this.amount = amount;
            this.type = type;
            this.createdAt = new Date();
            this.updatedAt = new Date();
        }

}
