package br.com.ericeol.suambank.entities;

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
    private int id;

    @ManyToOne
    private Bank bank;

    private Double balance = 0d;

    @Column(nullable = false, unique = true)
    private Long agencyNumber;

    @Column(nullable = false, unique = true)
    private Long accountNumber;

    private String accountType;

    @ManyToOne
    private Client client;

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

    public String deposit(Double value) {
        if(value <= 0) return "Deposite um value válido";

        balance += value;
        return "DEPÓSITO no value de " + value + " realizado com sucesso";
    }

    public String withdraw(Double value) {
        if(value > this.balance || value <= 0) throw new RuntimeException("Não é possível realizar o saque desse valor");

        balance -= value;
        return "SAQUE no value de " + value + " realizado com sucesso";
    }

    public String transfer(Account destinyAccount, Double value, TransfersType tipo ) {
        double valueComTaxa = value;

        if(tipo.equals(TransfersType.TED)) valueComTaxa = value + 15d;
        if(tipo.equals(TransfersType.DOC)) valueComTaxa = value + 10d;

        if(valueComTaxa > this.balance || value <= 0) return "Não é possível realizar uma transferência com esse value";

        this.withdraw(valueComTaxa);
        destinyAccount.deposit(value);

        return "TRANSFERÊNCIA no value de " + value + " para a Conta de " + destinyAccount.getClient().getName() + " realizada com sucesso";
    }

    public String getAccountType() {
        return this.accountType;
    }

    public int getId() {
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
