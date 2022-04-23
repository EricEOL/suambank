package br.com.ericeol.suambank.entities.DTO;


import br.com.ericeol.suambank.entities.Account.Account;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class AccountDTO {
    private Long id;
    private Long agencyNumber;
    private Long accountNumber;
    private String accountType;

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.agencyNumber = account.getAgencyNumber();
        this.accountNumber = account.getAccountNumber();
        this.accountType = account.getAccountType();
    }

    public static List<AccountDTO> convert(List<Account> accounts) {
        return accounts.stream().map(AccountDTO::new).collect(Collectors.toList());
    }

}
