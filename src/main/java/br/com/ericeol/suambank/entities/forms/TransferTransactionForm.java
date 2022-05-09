package br.com.ericeol.suambank.entities.forms;

import br.com.ericeol.suambank.entities.TransfersType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferTransactionForm {
    private Long accountId;
    private Long destinationAgencyNumber;
    private Long destinationAccountNumber;
    private Double value;
    private String transfersType;
    private String password;
}
