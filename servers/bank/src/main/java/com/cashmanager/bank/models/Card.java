package com.cashmanager.bank.models;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "cards")
@Getter
@Setter
@ToString
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String number;

    @Column
    private Date expiredAt;

    @Column(nullable = false)
    private String cvv;

    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @Column(nullable = false)
    private Date updatedAt;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne(mappedBy = "card")
    private BankAccount bankAccount;


    public Card() {
    }

    public Card(String number, Date expiredAt, String cvv) {
        this.number = number;
        this.expiredAt = expiredAt;
        this.cvv = cvv;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

}
