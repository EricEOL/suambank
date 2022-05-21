package br.com.ericeol.suambank.entities.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepositTransactionForm {

    @NotNull(message = "campo obrigatório")
    private Long agencyNumber;

    @NotNull(message = "campo obrigatório")
    private Long accountNumber;

    private Double value;
}
