package com.cashmanager.cash.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@ToString
public class ClientAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // how to upate this field automatically when one of the fields is updated?
    //@Column(nullable = false, updatable = true, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Column(nullable = false)
    private LocalDateTime updatedAt;

   // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne(cascade = CascadeType.ALL)
    private Client client;

    public ClientAccount() {
    }

    public ClientAccount(String email, String password) {
        this.email = email;
        this.password = password;

        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

}