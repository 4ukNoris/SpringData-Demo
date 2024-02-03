package com.example.springdataintrodemo.services;

import com.example.springdataintrodemo.models.Account;
import com.example.springdataintrodemo.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(BigDecimal money) {
        Account account = new Account(money);
        this.accountRepository.save(account);
        return account;
    }

    @Override
    public void withdrawMoney(BigDecimal money, Long id) {
        Optional<Account> account = this.accountRepository.findById(id);

        isExistAccount(account);

        BigDecimal current = account.get().getBalance();
        if (money.compareTo(current) > 0) { // Проверка дали парите които искам да истегля са достаъчни
            throw new RuntimeException("Not enough money!");
        }
        account.get().setBalance(current.subtract(money));// На текъщия акаунт сетвам новия баланс

        this.accountRepository.save(account.get()); //Записвам променения акаунт в базата
    }


    @Override
    public void depositMoney(BigDecimal money, Long id) {
        // isExistAccount(account);
        Account account = this.accountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(("Account does not exist")));

        BigDecimal amount = account.getBalance().add(money); // На акаунта взимаме баланса и добавяме пари
        account.setBalance(amount);// Update account with new amount

        this.accountRepository.save(account); //Записвам променения акаунт в базата
    }

    @Override
    public Account getAccountById(long accountId) {
        Optional<Account> account = this.accountRepository.findById(accountId);
        return account.orElse(null);
    }

    private static void isExistAccount(Optional<Account> account) {
        if (account.isEmpty()) { // Хвърлям ексепшън ако акаунта не съществува
            throw new RuntimeException("Account does not exist");
        }
    }
}
