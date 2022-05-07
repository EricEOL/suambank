package br.com.ericeol.suambank.controllers;

import br.com.ericeol.suambank.entities.DTO.AccountDTO;
import br.com.ericeol.suambank.entities.DTO.TransactionDTO;
import br.com.ericeol.suambank.entities.forms.FormAccount;
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
    public List<AccountDTO> all() {
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
    public void newCheckingAccount(@RequestBody FormAccount formAccount) {
        service.createCheckingAccount(formAccount);
    }

    @PostMapping("/savingsAccount")
    public void newSavingsAccount(@RequestBody FormAccount formAccount) {
        service.createSavingsAccount(formAccount);
    }

}
