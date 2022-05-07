package br.com.ericeol.suambank.entities.Account;

import br.com.ericeol.suambank.entities.*;
import br.com.ericeol.suambank.entities.Bank.Bank;
import br.com.ericeol.suambank.entities.transaction.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Bank bank;

    private Double balance = 0d;

    @Column(nullable = false)
    private Long agencyNumber;

    @Column(nullable = false, unique = true)
    private Long accountNumber;

    private String accountType;

    @ManyToOne
    private Client client;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    private List<Transaction> statement = new ArrayList<>();

    @OneToMany(mappedBy = "account")
    @JsonIgnore
    private List<Loan> loans = new ArrayList<>();

    public Account(Bank bank, Client client, String accountType, Long agencyNumber, Long accountNumber) {
        this.bank = bank;
        this.client = client;
        this.accountType = accountType;
        this.agencyNumber = agencyNumber;
        this.accountNumber = accountNumber;
    }

    public Boolean deposit(Double value) {
        if(value <= 0) throw new RuntimeException("Deposite um valor válido");

        balance += value;
        return true;
    }

    public Boolean withdraw(Double value) {
        if(value > this.balance || value <= 0) throw new RuntimeException("Não é possível realizar o saque desse valor");

        balance -= value;
        return true;
    }

    public Boolean transfer(Account destinyAccount, Double value, TransfersType transfersType ) {
        if(this.id == destinyAccount.getId()) throw new RuntimeException("Você não pode transferir valores para a mesma conta que está utilizando");

        TransfersType checkTransferTypeIsCorrect = checkIfTransferValueIsBetweenARangeOfChosenTransferType(transfersType, value);
        if(checkTransferTypeIsCorrect != transfersType) throw new RuntimeException("Não é possível transferir esse valor utilizando " + transfersType);

        double valueWithFee = value;

        if(transfersType.equals(TransfersType.TED)) valueWithFee = value + 15d;
        if(transfersType.equals(TransfersType.DOC)) valueWithFee = value + 10d;

        if(valueWithFee > this.balance || value <= 0) throw new RuntimeException("Essa conta não possui saldo suficiente para uma transferência com esse valor");

        this.withdraw(valueWithFee);
        destinyAccount.deposit(value);

        return true;
    }

    private TransfersType checkIfTransferValueIsBetweenARangeOfChosenTransferType(TransfersType transfersType, Double value) {
        if(value <= 2000d) return TransfersType.PIX;
        else if(value <= 5.000) return TransfersType.TED;
        else return TransfersType.DOC;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public Long getId() {
        return id;
    }

    public Bank getBank() {
        return bank;
    }

    public Double getBalance() {
        return balance;
    }

    public Long getAgencyNumber() {
        return agencyNumber;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public Client getClient() {
        return client;
    }
}
