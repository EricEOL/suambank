package br.com.ericeol.suambank.repositories;

import br.com.ericeol.suambank.entities.Bank.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {}
