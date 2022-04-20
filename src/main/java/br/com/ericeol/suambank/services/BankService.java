package br.com.ericeol.suambank.services;

import br.com.ericeol.suambank.entities.Bank;
import br.com.ericeol.suambank.entities.forms.FormBank;
import br.com.ericeol.suambank.repositories.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {

    @Autowired
    BankRepository repository;

    public List<Bank> all() {
        return repository.findAll();
    }

    public void createBank(FormBank form) {
        Bank bank = new Bank(form.getCod(), form.getName());
        repository.save(bank);
    }
}
