package br.com.ericeol.suambank.entities.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormClient {

    @NotBlank(message = "campo CPF é obrigatório")
    private String cpf;

    private String name;
}
