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

    @NotBlank(message = "campo NAME é obrigatório")
    private String name;

    @NotBlank(message = "campo PASSWORD é obrigatório")
    private String password;

    @NotBlank(message = "campo PASSWORD é obrigatório")
    private String email;
}
