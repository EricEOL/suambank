package br.com.ericeol.suambank.controllers;

import br.com.ericeol.suambank.entities.DTO.TokenDTO;
import br.com.ericeol.suambank.entities.forms.AuthenticationForm;
import br.com.ericeol.suambank.config.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public TokenDTO auth(@RequestBody @Valid AuthenticationForm form) {
        UsernamePasswordAuthenticationToken authData = form.transform();
        try {
            Authentication authentication = authenticationManager.authenticate(authData);
            String token = tokenService.generateToken(authentication);
            return new TokenDTO(token, "Bearer ");
        } catch (AuthenticationException e) {
            System.out.println("Controller Exception: " + e);
            throw new RuntimeException(e);
        }
    }
}
