package com.cashmanager.bank.models;

import jakarta.persistence.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "cards", uniqueConstraints = {
        @UniqueConstraint(columnNames = "number")
})
@Getter
@Setter
public class Card implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String expirationDate;

    @Column(nullable = false)
    private String cvv;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @JsonIgnore
    @OneToOne(mappedBy = "card")
    private BankAccount bankAccount;

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", cvv='" + cvv + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public Card() {
        super();
    }

    public Card(String number, String expirationDate, String cvv) {
        super();
        this.number = number;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

}
