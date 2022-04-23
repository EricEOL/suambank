package br.com.ericeol.suambank.controllers;

import br.com.ericeol.suambank.entities.DTO.TransactionDTO;
import br.com.ericeol.suambank.entities.forms.DepositTransactionForm;
import br.com.ericeol.suambank.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService service;

    @PostMapping("/deposit")
    public TransactionDTO deposit(@RequestBody DepositTransactionForm depositTransactionForm) {
        return service.deposit(depositTransactionForm);
    }

    @PostMapping("/withdraw")
    public TransactionDTO withdraw(@RequestBody Long accountId, @RequestBody Double value) {
        return service.withdraw(accountId, value);
    }
}
