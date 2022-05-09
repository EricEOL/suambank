package br.com.ericeol.suambank.entities.forms;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class LoanForm {

    @NotNull
    private Long accountId;

    @NotNull
    private Double initialValue;

    @NotNull
    private int installments;
}
