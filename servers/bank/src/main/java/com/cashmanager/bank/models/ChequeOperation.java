package com.cashmanager.bank.models;


import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "cheque_operations")
@Getter
@Setter
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

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @ManyToOne
        private BankAccount bankAccount;

}
