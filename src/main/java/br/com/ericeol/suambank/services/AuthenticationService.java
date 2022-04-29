package br.com.ericeol.suambank.services;

import br.com.ericeol.suambank.entities.Client;
import br.com.ericeol.suambank.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {

        Optional<Client> client = clientRepository.findByCpf(cpf);

        if(client.isPresent()) {
            return client.get();
        }

        throw new UsernameNotFoundException("O CPF ou a senha estão incorretos.");
    }
}
