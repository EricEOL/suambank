package br.com.ericeol.suambank.services;

import br.com.ericeol.suambank.entities.Account;
import br.com.ericeol.suambank.entities.AccountType;
import br.com.ericeol.suambank.entities.bank.Bank;
import br.com.ericeol.suambank.entities.Client;
import br.com.ericeol.suambank.repositories.AccountRepository;
import br.com.ericeol.suambank.repositories.BankRepository;
import br.com.ericeol.suambank.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountRepository repository;
    @Autowired
    BankRepository bankRepository;
    @Autowired
    ClientRepository clientRepository;

    public List<Account> all() {
        return repository.findAll();
    }

    public void createCheckingAccount(Long clientId) {
        try {
            Bank bank = bankRepository.findById(1L).get();
            Client client = clientRepository.findById(clientId).get();

            Long agencyNumber = Math.round((repository.count() + 1) * Math.random() * 1000);
            Long accountNumber = Math.round((repository.count() + 2) * Math.random() * 1007);

            Account account = new Account(bank, client , AccountType.CHECKING.toString(), agencyNumber, accountNumber);
            repository.save(account);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void createSavingsAccount(Long clientId) {
        try {
            Bank bank = bankRepository.findById(1L).get();
            Client client = clientRepository.findById(clientId).get();

            Long agencyNumber = Math.round((repository.count() + 1) * Math.random() * 1001);
            Long accountNumber = Math.round((repository.count() + 2) * Math.random() * 1003);

            Account account = new Account(bank, client , AccountType.SAVINGS.toString(), agencyNumber, accountNumber);
            repository.save(account);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
