package br.com.ericeol.suambank.repositories;

import br.com.ericeol.suambank.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    public Optional<Client> findByCpf(String cpf);
}
