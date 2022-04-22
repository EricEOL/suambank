package br.com.ericeol.suambank.repositories;

import br.com.ericeol.suambank.entities.Account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT ac from Account ac WHERE ac.agencyNumber = ?1 AND ac.accountNumber = ?2")
    Account findByAgencyAndAccountNumber(Long agencyNumber, Long accountNumber);
}
