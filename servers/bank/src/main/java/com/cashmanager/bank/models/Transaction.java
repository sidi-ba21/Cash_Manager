package com.cashmanager.bank.models;

import com.cashmanager.bank.models.enums.TransactionAction;
import com.cashmanager.bank.models.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

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

        @Column(nullable = false)
        @Enumerated(EnumType.STRING)
        private TransactionAction action;

        @Column(nullable = false, updatable = false)
        private LocalDateTime createdAt;

        @Column(nullable = false)
        private LocalDateTime updatedAt;

        @ManyToOne
        private BankAccount bankAccount;

        public Transaction() {
        }

        public Transaction(Long amount, TransactionType type, TransactionAction action) {
            this.amount = amount;
            this.type = type;
            this.action = action;

            LocalDateTime now = LocalDateTime.now();
            this.createdAt = now;
            this.updatedAt = now;
        }

}
