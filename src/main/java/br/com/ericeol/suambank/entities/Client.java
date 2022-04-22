package br.com.ericeol.suambank.entities;

import br.com.ericeol.suambank.entities.Account.Account;
import br.com.ericeol.suambank.entities.Account.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String cpf;

    private String name;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Account> accounts = new ArrayList<>();

    public Client(String cpf, String name) {
        this.cpf = cpf;
        this.name = name;
    }

    public Boolean clientAlreadyHasCheckingAccount() {
        List<Account> result = this.accounts
                .stream()
                .filter(account -> account.getAccountType().length() == AccountType.CHECKING.toString().length())
                .collect(Collectors.toList());

        return result.size() >= 1;
    }

    public Boolean clientAlreadyHasSavingsAccount() {
        List<Account> result = this.accounts
                .stream()
                .filter(account -> account.getAccountType().length() == AccountType.SAVINGS.toString().length())
                .collect(Collectors.toList());

        return result.size() >= 1;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
