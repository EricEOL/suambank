package br.com.ericeol.suambank.entities.DTO;

import br.com.ericeol.suambank.entities.Account.Account;
import br.com.ericeol.suambank.entities.Transaction;
import br.com.ericeol.suambank.entities.TransactionsType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private Long agencyNumber;
    private Long accountNumber;
    private Double value;
    private TransactionsType transactionType;
    private String status = "success";

    public TransactionDTO(Account account, Double value, TransactionsType transactionType, String status) {
        this.agencyNumber = account.getAgencyNumber();
        this.accountNumber = account.getAccountNumber();
        this.value = value;
        this.transactionType = transactionType;
        this.status = status;
    }

    public TransactionDTO(Transaction transaction) {
        this.agencyNumber = transaction.getAgencyNumber();
        this.accountNumber = transaction.getAccountNumber();
        this.value = transaction.getValue();
        this.transactionType = transaction.getTransactionsType();
    }

    public static List<TransactionDTO> convert(List<Transaction> transactions) {
        return transactions.stream().map(TransactionDTO::new).collect(Collectors.toList());
    }
}
