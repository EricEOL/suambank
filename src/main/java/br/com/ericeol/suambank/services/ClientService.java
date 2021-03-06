package br.com.ericeol.suambank.services;

import br.com.ericeol.suambank.entities.Client;
import br.com.ericeol.suambank.entities.DTO.ClientDTO;
import br.com.ericeol.suambank.entities.forms.FormClient;
import br.com.ericeol.suambank.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
        Client client = new Client(form.getCpf(), form.getName(), new BCryptPasswordEncoder().encode(form.getPassword()));
        repository.save(client);
    }

    public ClientDTO one(Long id) {
        try {
            Client client = repository.findById(id).get();
            return new ClientDTO(client);

        } catch (Exception e) {
            throw new RuntimeException("Não existe cliente com esse id");
        }
    }
}
