package com.cashmanager.bank.models;

import jakarta.persistence.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
public class Transaction implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private Long amount;

        @Column(nullable = false)
        private String type;

        @Column(nullable = false, updatable = false)
        private LocalDateTime createdAt;

        @Column(nullable = false)
        private LocalDateTime updatedAt;

        @JsonIgnore
        @ManyToOne
        private BankAccount bankAccount;

        @Override
        public String toString() {
            return "Transaction{" +
                    "id=" + id +
                    ", amount='" + amount + '\'' +
                    ", type='" + type + '\'' +
                    ", createdAt=" + createdAt +
                    ", updatedAt=" + updatedAt +
                    '}';
        }

        public Transaction() {
            super();
        }

        public Transaction(Long amount, String type) {
            super();
            this.amount = amount;
            this.type = type;
            this.createdAt = LocalDateTime.now();
            this.updatedAt = LocalDateTime.now();
        }
}
