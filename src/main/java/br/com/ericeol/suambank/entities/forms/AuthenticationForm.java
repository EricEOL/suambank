package br.com.ericeol.suambank.entities.forms;

import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AuthenticationForm {

    @NotNull @NotEmpty
    private String cpf;

    @NotNull @NotEmpty
    private String password;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UsernamePasswordAuthenticationToken transform() {
        return new UsernamePasswordAuthenticationToken(cpf, password);
    }
}
