package br.com.ericeol.suambank;

import br.com.ericeol.suambank.controllers.TransactionController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransactionTest {

    @Autowired
    TransactionController controller;

    @Test
    void shouldBeDepositValueInAccountBalance() {
        controller.deposit(1L, 3000d);
    }

}
