package br.com.ericeol.suambank.config.security;

import br.com.ericeol.suambank.entities.Client;
import br.com.ericeol.suambank.repositories.ClientRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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

        String token = this.getToken(request);

        boolean isTokenValid = tokenService.isTokenValid(token);

        if(isTokenValid) {
            this.authenticateUser(token);
        }

        filterChain.doFilter(request, response);
    }

    private void authenticateUser(String token) {
        Long userId = tokenService.getUserId(token);

        Client client = clientRepository.findById(userId).get();

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(client, null, client.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String getToken(HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7, token.length());
    }
}
