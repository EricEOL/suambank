package br.com.ericeol.suambank;

import br.com.ericeol.suambank.controllers.ClientController;
import br.com.ericeol.suambank.entities.forms.FormClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClientTest {

    @Autowired
    ClientController clientController;

    @Test
    void getRequestShouldReturnDataOfClients() {
        System.out.println(clientController.all());
    }

    @Test
    void createNewClient() {
        FormClient formClient = new FormClient("222.222.222-02", "Pipoca");
        clientController.newClient(formClient);
    }

}
