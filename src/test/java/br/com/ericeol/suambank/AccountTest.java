package br.com.ericeol.suambank;

import br.com.ericeol.suambank.controllers.AccountController;
import br.com.ericeol.suambank.controllers.ClientController;
import br.com.ericeol.suambank.entities.Account.AccountType;
import br.com.ericeol.suambank.entities.Account.CreateNewAccountException;
import br.com.ericeol.suambank.entities.Client;
import br.com.ericeol.suambank.repositories.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class AccountTest {

    @Autowired
    AccountController accountController;

    @Autowired
    ClientRepository clientRepository;

    @Test
    @Transactional
    void shouldReturnAllAccounts() {
        System.out.println(accountController.all());
    }

    @Test
    void createNewCheckingAccount() {
        accountController.newCheckingAccount(1L);
    }

    @Test
    void createNewSavingsAccount() {
        accountController.newSavingsAccount(1L);
    }

    @Test
    @Transactional
    void shouldNotBePossibleCreateTwoCheckingAccountsForTheSameClient() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            accountController.newCheckingAccount(1L);
        });
    }

}
