package br.com.ericeol.suambank.entities.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormBank {
    @NotBlank(message = "campo obrigatório")
    @Size(min = 3, max = 5, message = "campo deve ter entre 3 e 5 caracteres")
    private String cod;

    @NotBlank(message = "campo obrigatório")
    private String name;
}
