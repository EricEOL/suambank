package br.com.ericeol.suambank.services;

import br.com.ericeol.suambank.entities.Account.Account;
import br.com.ericeol.suambank.entities.Bank.Bank;
import br.com.ericeol.suambank.entities.Bank.CreateNewBankException;
import br.com.ericeol.suambank.entities.forms.FormBank;
import br.com.ericeol.suambank.entities.forms.LoanForm;
import br.com.ericeol.suambank.repositories.AccountRepository;
import br.com.ericeol.suambank.repositories.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BankService {

    @Autowired
    BankRepository repository;

    @Autowired
    AccountRepository accountRepository;

    public List<Bank> all() {
        return repository.findAll();
    }

    @Transactional
    public void createBank(FormBank form) {
        Boolean isBankRegistered = repository.count() >= 1;

        if(isBankRegistered) {
            throw new CreateNewBankException("Não é possível criar outro banco.");
        }

        Bank bank = new Bank(form.getCod(), form.getName());
        repository.save(bank);
    }

    @Transactional
    public void newLoan(LoanForm loanForm) {
        Bank bank = repository.getById(1L);
        Optional<Account> account = accountRepository.findById(loanForm.getAccountId());

        if(account.isEmpty()) throw new RuntimeException("Não existe conta com o id: " + loanForm.getAccountId());

        bank.takeOutLoan(account.get(), loanForm.getValue(), loanForm.getInstallments());
    }
}
