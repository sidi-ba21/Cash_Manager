package com.cashmanager.bank.models;


import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
public class ChequeOperation {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private Long amount;

        @Column(nullable = false)
        private String type;

        @Column(nullable = false, updatable = false)
        private Date createdAt;

        @Column(nullable = false)
        private Date updatedAt;

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @ManyToOne
        private BankAccount bankAccount;

        public ChequeOperation() {
        }

        public ChequeOperation(Long amount, String type) {
            this.amount = amount;
            this.type = type;
            this.createdAt = new Date();
            this.updatedAt = new Date();
        }
}
