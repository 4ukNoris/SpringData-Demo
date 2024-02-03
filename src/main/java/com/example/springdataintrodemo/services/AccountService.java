package com.example.springdataintrodemo.services;

import com.example.springdataintrodemo.models.Account;

import java.math.BigDecimal;

public interface AccountService {

    Account createAccount(BigDecimal money);

    void withdrawMoney(BigDecimal money, Long id);

    void depositMoney(BigDecimal money, Long id);

    Account getAccountById(long accountId);
}
