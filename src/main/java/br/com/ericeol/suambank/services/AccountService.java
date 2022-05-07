package br.com.ericeol.suambank.services;

import br.com.ericeol.suambank.entities.Account.Account;
import br.com.ericeol.suambank.entities.Account.AccountType;
import br.com.ericeol.suambank.entities.Account.CreateNewAccountException;
import br.com.ericeol.suambank.entities.Bank.Bank;
import br.com.ericeol.suambank.entities.Client;
import br.com.ericeol.suambank.entities.DTO.AccountDTO;
import br.com.ericeol.suambank.entities.DTO.TransactionDTO;
import br.com.ericeol.suambank.entities.transaction.Transaction;
import br.com.ericeol.suambank.entities.forms.FormAccount;
import br.com.ericeol.suambank.repositories.AccountRepository;
import br.com.ericeol.suambank.repositories.BankRepository;
import br.com.ericeol.suambank.repositories.ClientRepository;
import br.com.ericeol.suambank.utils.GenerateAgencyNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    AccountRepository repository;
    @Autowired
    BankRepository bankRepository;
    @Autowired
    ClientRepository clientRepository;

    public List<AccountDTO> all() {
        return AccountDTO.convert(repository.findAll());
    }

    @Transactional
    public void createCheckingAccount(FormAccount formAccount) {
        try {
            Bank bank = bankRepository.findById(1L).get();
            Client client = clientRepository.findById(formAccount.getClientId()).get();

            Boolean clientAlreadyHasCheckingAccount = client.clientAlreadyHasCheckingAccount();

            if(clientAlreadyHasCheckingAccount) {
                throw new CreateNewAccountException("Esse cliente já possui uma checking account");
            }

            Long agencyNumber = GenerateAgencyNumber.generate(repository);
            Long accountNumber = Math.round((repository.count() + 2) * Math.random() * 1007);

            Account account = new Account(bank, client , AccountType.CHECKING.toString(), agencyNumber, accountNumber);
            repository.save(account);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void createSavingsAccount(FormAccount formAccount) {
        try {
            Bank bank = bankRepository.findById(1L).get();
            Client client = clientRepository.findById(formAccount.getClientId()).get();

            Boolean clientAlreadyHasSavingsAccount = client.clientAlreadyHasSavingsAccount();

            if(clientAlreadyHasSavingsAccount) {
                throw new CreateNewAccountException("Esse cliente já possui uma savings account");
            }

            Long agencyNumber = GenerateAgencyNumber.generate(repository);
            Long accountNumber = Math.round((repository.count() + 2) * Math.random() * 1003);

            Account account = new Account(bank, client , AccountType.SAVINGS.toString(), agencyNumber, accountNumber);
            repository.save(account);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TransactionDTO> statement(Long accountId, int month, int year) {
        Optional<Account> account = repository.findById(accountId);

        if(account.isEmpty()) {
            throw new RuntimeException("Não existe conta com esse id");
        }

        List<Transaction> transactions = account.get().getStatement().stream().filter(transaction -> {
            int transactionMonth = transaction.getCreatedAt().getMonth() + 1;
            int transactionYear = transaction.getCreatedAt().getYear() + 1900;

            return transactionMonth == month && transactionYear == year;
        }).collect(Collectors.toList());

        return TransactionDTO.convert(transactions);
    }
}
