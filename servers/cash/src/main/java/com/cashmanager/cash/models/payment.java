package com.cashmanager.cash.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "payments")
@Getter
@Setter
@ToString
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private string type;

    @Column
    private Boolean allowed;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne(mappedBy = "payment")
    private Order order;

    public Payment() {
    }

    public Payment(string type, Boolean allowed) {
        this.type = type;
        this.allowed = allowed;
    }
}