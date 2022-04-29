package br.com.ericeol.suambank.config.security;

import br.com.ericeol.suambank.repositories.ClientRepository;
import br.com.ericeol.suambank.services.TokenService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private ClientRepository clientRepository;

    public AuthenticationTokenFilter(TokenService tokenService, ClientRepository clientRepository) {
        this.tokenService = tokenService;
        this.clientRepository = clientRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    }
}
