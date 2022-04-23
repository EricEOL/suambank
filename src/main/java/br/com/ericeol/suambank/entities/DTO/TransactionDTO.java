package br.com.ericeol.suambank.entities.DTO;

import br.com.ericeol.suambank.entities.Account.Account;
import br.com.ericeol.suambank.entities.TransactionsType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private Long agencyNumber;
    private Long accountNumber;
    private Double value;
    private TransactionsType transactionType;
    private String status;

    public TransactionDTO(Account account, Double value, TransactionsType transactionType, String status) {
        this.agencyNumber = account.getAgencyNumber();
        this.accountNumber = account.getAccountNumber();
        this.value = value;
        this.transactionType = transactionType;
        this.status = status;
    }
}
