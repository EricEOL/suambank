package br.com.ericeol.suambank.repositories;

import br.com.ericeol.suambank.entities.Account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {}
