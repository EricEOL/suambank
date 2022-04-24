package br.com.ericeol.suambank.controllers;

import br.com.ericeol.suambank.entities.Account.Account;
import br.com.ericeol.suambank.entities.DTO.TransactionDTO;
import br.com.ericeol.suambank.entities.Transaction;
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

    @GetMapping("/statement/{accountId}")
    public List<TransactionDTO> statementByMonth(
            @PathVariable Long accountId,
            @RequestParam int month,
            @RequestParam int year) {

        return service.statement(accountId, month, year);
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
