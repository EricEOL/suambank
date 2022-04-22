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
        FormClient formClient = new FormClient("111.111.111-01", "Rebecca");
        clientController.newClient(formClient);
    }

}
