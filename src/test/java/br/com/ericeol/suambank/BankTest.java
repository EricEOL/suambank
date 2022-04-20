package br.com.ericeol.suambank;

import br.com.ericeol.suambank.controllers.BankController;
import br.com.ericeol.suambank.entities.forms.FormBank;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BankTest {

    @Autowired
    BankController bankController;

    @Test
    @Order(2)
    void getMappingShouldReturnStatusOKAndDataOfBank() {
        System.out.println(bankController.all());
    }

}
