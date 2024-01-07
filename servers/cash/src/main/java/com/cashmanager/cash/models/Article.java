package com.cashmanager.cash.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "articles")
@Getter
@Setter
@ToString
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long price = 0L;


    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    private Cart cart;

   // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    private Order order;


    public Article() {
    }

    public Article(String name, Long price) {
        this.name = name;
        this.price = price;
    }
}