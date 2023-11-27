package com.cashmanager.bank.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "cheques")
public class Cheque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String number;

    @Column(nullable = false, updatable = false)
    private Date expiredAt;
    
}
