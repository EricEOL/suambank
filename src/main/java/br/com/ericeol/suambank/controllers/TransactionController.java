package br.com.ericeol.suambank.controllers;

import br.com.ericeol.suambank.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService service;

    @GetMapping("/deposit")
    public String deposit(@RequestBody Long id, @RequestBody Double value) {
        return service.deposit(id, value);
    }
}
