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
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String cod;

    @Column(nullable = false, unique = true)
    private String name;

    private Double bankBalance = 10000000000000000d;

    @OneToMany(mappedBy = "bank", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Account> accounts = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Loan> loans = new ArrayList<>();

    public Bank(String cod, String name) {
        this.cod = cod;
        this.name = name;
    }

    public Account newCheckingAccount(Client client) {
        for (Account account : accounts) {
            if (account.getClient().getCpf() == client.getCpf() && account.getAccountType() == AccountType.CHECKING.toString()) {
                throw new RuntimeException("Não é possível criar duas contas correntes para o mesmo cliente");
            }
        }

        Account account = new Account(this, client, AccountType.CHECKING);

        client.addAccount(account);

        accounts.add(account);

        return account;
    }

    public Account newSavingsAccount(Client client) {

        for (Account account : accounts) {
            if (account.getClient().getCpf() == client.getCpf() && account.getAccountType() == AccountType.SAVINGS.toString()) {
                throw new RuntimeException("Não é possível criar duas contas poupança para o mesmo cliente");
            }
        }

        Account account = new Account(this, client, AccountType.SAVINGS);

        client.addAccount(account);

        accounts.add(account);

        return account;
    }

    public Loan takeOutLoan(Account requestedAccount, Double requestedValue, int installments) {
        if (requestedValue > 30000d) throw new RuntimeException("Value solicitado excede o permitido");

        if (!accounts.contains(requestedAccount))
            throw new RuntimeException("O empréstimo só é realizado para contas relacionadas ao banco, contas de outros bancos não são permitidas");

        Loan loan = new Loan(requestedAccount, requestedValue, installments);
        requestedAccount.deposit(requestedValue);
        this.setBankBalance(bankBalance - requestedValue);
        this.loans.add(loan);

        return loan;
    }

    public String getCod() {
        return cod;
    }

    public String getName() {
        return name;
    }

    public Double getBankBalance() {
        return bankBalance;
    }

    public void setBankBalance(Double bankBalance) {
        this.bankBalance = bankBalance;
    }

    public Double getAccountsBalance() {
        Double accountsBalance = 0d;
        for(Account account: accounts) accountsBalance += account.getBalance();
        return accountsBalance;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void verifyLoan(Loan loan) {
        if (loan.getInstallmentsPaid() == loan.getInstallments()) loans.remove(loan);
    }

    @Override
    public String toString() {
        return "Bank{" +
                "cod='" + cod + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
