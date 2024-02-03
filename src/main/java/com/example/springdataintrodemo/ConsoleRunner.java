package com.example.springdataintrodemo;

import com.example.springdataintrodemo.models.Account;
import com.example.springdataintrodemo.models.User;
import com.example.springdataintrodemo.services.AccountService;
import com.example.springdataintrodemo.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final UserService userService;
    private final AccountService accountService;

    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        String username = scanner.nextLine();

        BigDecimal money = new BigDecimal(scanner.nextLine());


        Account account = new Account(money);
        User user = this.userService.getUserByUsername(username);

        if (user == null) {
            System.out.print("Read your age: ");
            int age = Integer.parseInt(scanner.nextLine());
            user = new User(username, age);
            this.userService.registerUser(user);
        }
        account.setUser(user);
        user.setAccounts(new HashSet<>());
        user.addAccount(account);
    }
}
