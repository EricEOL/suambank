package br.com.ericeol.suambank;

import br.com.ericeol.suambank.controllers.TransactionController;
import br.com.ericeol.suambank.entities.forms.DepositTransactionForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransactionTest {

    @Autowired
    TransactionController controller;

    @Test
    void shouldBeDepositValueInAccountBalance() {
        DepositTransactionForm depositTransactionForm = new DepositTransactionForm(1573L, 2043L, 3000d);
        System.out.println(controller.deposit(depositTransactionForm));
    }

    @Test
    void shouldBeWithdrawValueFromAccountBalance() {
        System.out.println(controller.withdraw(1L, 290d));
    }

}
