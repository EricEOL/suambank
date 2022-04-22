package br.com.ericeol.suambank.services;

import br.com.ericeol.suambank.entities.Account.Account;
import br.com.ericeol.suambank.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

    @Autowired
    AccountRepository repository;

    @Transactional
    public String deposit(Long accountId, Double value) {
        try {
            Account account = repository.findById(accountId).get();

            return account.deposit(value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
