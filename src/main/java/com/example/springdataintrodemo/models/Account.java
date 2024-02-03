package com.example.springdataintrodemo.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "acconts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private BigDecimal balance;
    @ManyToOne
    private User user;

    public Account() {
        // Здаваме дефолтна стойност 0 (не искам да бъде null)
        this.balance = BigDecimal.ZERO;
    }

    public Account(BigDecimal balance) {
        this();
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
