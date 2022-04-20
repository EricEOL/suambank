package br.com.ericeol.suambank.controllers;

import br.com.ericeol.suambank.entities.Client;
import br.com.ericeol.suambank.entities.forms.FormClient;
import br.com.ericeol.suambank.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService service;

    @GetMapping
    public List<Client> all() {
        return service.all();
    }

    @PostMapping
    public void newClient(@RequestBody FormClient form) {
        service.createClient(form);
    }

}
