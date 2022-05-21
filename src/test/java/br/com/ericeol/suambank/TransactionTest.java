package br.com.ericeol.suambank;

import br.com.ericeol.suambank.controllers.TransactionController;
import br.com.ericeol.suambank.entities.forms.DepositTransactionForm;
import br.com.ericeol.suambank.entities.forms.TransferTransactionForm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
/*
@SpringBootTest
public class TransactionTest {

    @Autowired
    TransactionController controller;

    @Test
    void shouldBeDepositValueInAccountBalance() {
        DepositTransactionForm depositTransactionForm = new DepositTransactionForm(1000L, 548L, 3000d);
        System.out.println(controller.deposit(depositTransactionForm));
    }

    @Test
    void shouldBeWithdrawValueFromAccountBalance() {
        System.out.println(controller.withdraw(1L, 290d));
    }

    @Test
    void shouldBeTransferValueFromSenderAccountToDestinationAccountUsingTransfersTypePIX() {
        TransferTransactionForm transferTransactionForm = new TransferTransactionForm(
                2L,
                1000L,
                984L,
                2000d,
                "PIX"
        );
        System.out.println(controller.transfer(transferTransactionForm));
    }

    @Test
    void shouldBeTransferValueFromSenderAccountToDestinationAccountUsingTransfersTypeTED() {
        TransferTransactionForm transferTransactionForm = new TransferTransactionForm(
                2L,
                1000L,
                984L,
                4001d,
                "TED"
        );
        System.out.println(controller.transfer(transferTransactionForm));
    }

    @Test
    void shouldBeTransferValueFromSenderAccountToDestinationAccountUsingTransfersTypeDOC() {
        TransferTransactionForm transferTransactionForm = new TransferTransactionForm(
                2L,
                829L,
                991L,
                1000d,
                "DOC"
        );
        System.out.println(controller.transfer(transferTransactionForm));
    }

    @Test
    void shouldNotBePossibleTransferValueWhenTheSenderAccountIsEqualsToDestinationAccount() {
        TransferTransactionForm transferTransactionForm = new TransferTransactionForm(
                1L,
                829L,
                991L,
                2000d,
                "PIX"
        );
        Assertions.assertThrows(RuntimeException.class, () -> controller.transfer(transferTransactionForm));
    }

}
*/
