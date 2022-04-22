package br.com.ericeol.suambank;

import br.com.ericeol.suambank.controllers.AccountController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountTest {

    @Autowired
    AccountController accountController;

    @Test
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

}
