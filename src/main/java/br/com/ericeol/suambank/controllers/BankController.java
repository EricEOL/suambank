package br.com.ericeol.suambank.controllers;

import br.com.ericeol.suambank.entities.Bank.Bank;
import br.com.ericeol.suambank.entities.forms.FormBank;
import br.com.ericeol.suambank.entities.forms.LoanForm;
import br.com.ericeol.suambank.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    BankService service;

    @GetMapping
    public List<Bank> all() {
        return service.all();
    }

    @PostMapping
    public void newBank(@RequestBody FormBank form) {
        service.createBank(form);
    }

    @PostMapping("/loan")
    public void newLoan(@RequestBody @Valid LoanForm loanForm) {
        service.newLoan(loanForm);
    }

}
