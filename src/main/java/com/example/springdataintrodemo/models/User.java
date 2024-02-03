package com.example.springdataintrodemo.models;

import jakarta.persistence.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String username;
    @Column
    private int age;

    @OneToMany(targetEntity = Account.class, mappedBy = "user")
    private Set<Account> accounts;

    public User() {
        this.accounts = new HashSet<>();
    }

    public User(String username, int age) {
        this();
        this.username = username;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Account> getAccounts() {
        return Collections.unmodifiableSet(accounts);
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public void removeAccount(Account account) {
        this.accounts.remove(account);
    }
}
