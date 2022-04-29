package br.com.ericeol.suambank.services;

import br.com.ericeol.suambank.entities.Client;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${suambank.jwt.secret}")
    private String secret;

    @Value("${suambank.jwt.expiration}")
    private String expiration;

    public String generateToken(Authentication authentication) {
        Client loggedClient = (Client) authentication.getPrincipal();

        Date today = new Date();
        Date dateExpiration = new Date(today.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("API-SUAMBANK")
                .setSubject(loggedClient.getId().toString())
                .setIssuedAt(today)
                .setExpiration(dateExpiration)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

    }

    public Boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJwt(token);
            return true;
        } catch (Exception e) {
            System.out.println("Exception na validação do Token: " + e);
            return false;
        }
    }

    public Long getUserId(String token) {
        Claims body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(body.getSubject());
    }

}
