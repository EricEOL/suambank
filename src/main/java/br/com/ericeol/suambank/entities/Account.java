package br.com.ericeol.suambank.entities;

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
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Bank bank;

    private Double balance = 0d;

    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String agencyNumber;

    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String accountNumber;

    private String accountType;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "account")
    private List<Loan> loans = new ArrayList<>();

    public Account(Bank bank, Client client, AccountType type) {
        this.bank = bank;
        this.client = client;
        this.accountType = type.toString();
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

    public String getAgencyNumber() {
        return agencyNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Client getClient() {
        return client;
    }
}
