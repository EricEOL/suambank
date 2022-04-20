package br.com.ericeol.suambank.services;

import br.com.ericeol.suambank.entities.Account;
import br.com.ericeol.suambank.entities.Bank;
import br.com.ericeol.suambank.entities.forms.FormAccount;
import br.com.ericeol.suambank.repositories.AccountRepository;
import br.com.ericeol.suambank.repositories.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    AccountRepository repository;

    @Autowired
    BankRepository bankRepository;

    /*
    public void createAccount(FormAccount form) {
        Bank bank = bankRepository.findById(1L).get();

        Account account = new Account(bank, form.getBankCod());
        repository.save(account);
    }*/

}
