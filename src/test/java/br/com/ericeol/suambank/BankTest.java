package br.com.ericeol.suambank;

import br.com.ericeol.suambank.controllers.BankController;
import br.com.ericeol.suambank.entities.bank.CreateNewBankException;
import br.com.ericeol.suambank.entities.forms.FormBank;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BankTest {

    @Autowired
    BankController bankController;

    @Test
    void getMappingShouldReturnStatusOKAndDataOfBank() {
        System.out.println(bankController.all());
    }

    @Test
    void shouldBeCreatedANewBank() {
        FormBank formBank = new FormBank("001", "Suambank");
        bankController.newBank(formBank);
    }

    @Test
    void shouldNotBePossibleCreateANewBankAfterOneBankHasBeenRegistered() {
        FormBank formBank = new FormBank("001", "Suambank");

        Assertions.assertThrows(CreateNewBankException.class, () -> {
            bankController.newBank(formBank);
        });
    }
}
