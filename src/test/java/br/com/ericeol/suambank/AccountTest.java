package br.com.ericeol.suambank;

import br.com.ericeol.suambank.controllers.AccountController;
import br.com.ericeol.suambank.entities.forms.FormAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class AccountTest {

    @Autowired
    AccountController accountController;

    @Test
    @Transactional
    void shouldReturnAllAccounts() {
        System.out.println(accountController.all());
    }

    @Test
    @Transactional
    void createNewCheckingAccount() {
        accountController.newCheckingAccount(new FormAccount(2L));
    }

    @Test
    @Transactional
    void createNewSavingsAccount() {
        accountController.newSavingsAccount(new FormAccount(1L));
    }

    @Test
    @Transactional
    void shouldNotBePossibleCreateTwoCheckingAccountsForTheSameClient() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            accountController.newCheckingAccount(new FormAccount(1L));
        });
    }

    @Test
    @Transactional
    void shouldNotBePossibleCreateTwoSavingsAccountsForTheSameClient() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            accountController.newSavingsAccount(new FormAccount(1L));
        });
    }

    @Test
    @Transactional
    void shouldBeReturnAccountStatement() {
        System.out.println(accountController.statementByMonth(2L, 4, 2022));
    }

}
