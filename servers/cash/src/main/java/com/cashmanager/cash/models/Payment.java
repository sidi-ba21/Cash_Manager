package com.cashmanager.cash.models;

import com.cashmanager.cash.models.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@Setter
@ToString
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private  TransactionType type;

    @Column(nullable = false)
    private Boolean allowed;

    @Column(updatable = false)
    private LocalDateTime createdAt;


   // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne(cascade = CascadeType.ALL)
    private Order order;

    public Payment() {
    }

    public Payment(TransactionType type, Boolean allowed) {
        this.type = type;
        this.allowed = allowed;

        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
    }

}
