package com.cashmanager.cash.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long totalPrice;

    @Column(updatable = false)
    private Date createdAt;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @manyToOne
    private Client client;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "order")
    private List<Article> articles = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne(cascade = CascadeType.ALL)
    private Payment payment;

    public Order() {
    }

    public Order(Long totalPrice) {
        this.totalPrice = totalPrice;
        this.createdAt = new Date();
    }

}