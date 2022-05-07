package br.com.ericeol.suambank.entities.transaction;

import br.com.ericeol.suambank.entities.Account.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    private Long agencyNumber;
    private Long accountNumber;
    private Double value;
    private TransactionsType transactionsType;
    private TransactionStatus transactionStatus;
    private Timestamp createdAt;

    public Transaction(Account account, Long agencyNumber, Long accountNumber, Double value, TransactionsType transactionsType, TransactionStatus transactionStatus) {
        this.account = account;
        this.agencyNumber = agencyNumber;
        this.accountNumber = accountNumber;
        this.value = value;
        this.transactionsType = transactionsType;
        this.transactionStatus = transactionStatus;
        this.createdAt = Timestamp.from(Instant.now());
    }
}
