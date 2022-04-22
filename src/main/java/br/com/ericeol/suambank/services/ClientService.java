package br.com.ericeol.suambank.services;

import br.com.ericeol.suambank.entities.Client;
import br.com.ericeol.suambank.entities.dto.ClientDTO;
import br.com.ericeol.suambank.entities.forms.FormClient;
import br.com.ericeol.suambank.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepository repository;

    public List<ClientDTO> all() {
        List<Client> clients = repository.findAll();
        return ClientDTO.convert(clients);
    }

    public void createClient(FormClient form) {
        Client client = new Client(form.getCpf(), form.getName());
        repository.save(client);
    }

}
