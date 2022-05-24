package br.com.ericeol.suambank.entities.forms;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class WithdrawTransactionForm {
    @NotNull(message = "campo obrigatório")
    private Long accountId;

    @NotNull(message = "campo obrigatório")
    private Double value;
}
