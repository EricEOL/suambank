package br.com.ericeol.suambank.controllers;

import br.com.ericeol.suambank.entities.Account;
import br.com.ericeol.suambank.entities.Client;
import br.com.ericeol.suambank.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService service;

    @GetMapping
    public List<Account> all() {
        return service.all();
    }

    @PostMapping("/checkingAccount")
    public void newCheckingAccount(@RequestBody Long clientId) {
        service.createCheckingAccount(clientId);
    }

    @PostMapping("/savingsAccount")
    public void newSavingsAccount(@RequestBody Long clientId) {
        service.createSavingsAccount(clientId);
    }

}
