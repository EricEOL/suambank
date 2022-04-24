package br.com.ericeol.suambank.repositories;

import br.com.ericeol.suambank.entities.Account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT ac FROM Account ac WHERE ac.agencyNumber = ?1 AND ac.accountNumber = ?2")
    Account findByAgencyAndAccountNumber(Long agencyNumber, Long accountNumber);

    @Query("SELECT COUNT(ac) FROM Account ac WHERE ac.agencyNumber = ?1")
    Long countAccountsFromAgency(Long lastAgencyNumber);

    @Query("SELECT MAX(ac.id) FROM Account ac")
    Long lastAccount();
}
