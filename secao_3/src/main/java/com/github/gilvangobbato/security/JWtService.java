package com.github.gilvangobbato.security;

import com.github.gilvangobbato.domain.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.security.KeyPairGenerator;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JWtService {

    @Value("${security.jwt.expiracao}")
    private String expiracao;
    @Value("${security.jwt.chave-assinatura}")
    private String chaveAssinatura;

    public String gerarToken(User user) {
        long exp = Long.valueOf(expiracao);
        LocalDateTime time = LocalDateTime.now().plusMinutes(exp);
        Date dt = Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setExpiration(dt)
                .signWith(SignatureAlgorithm.HS512, chaveAssinatura)
                .compact();
    }

    public Claims obterClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(chaveAssinatura)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token){
        try{
            Date dt = this.obterClaims(token).getExpiration();
            LocalDateTime time = LocalDateTime.ofInstant(dt.toInstant(), ZoneId.systemDefault());
            return !LocalDateTime.now().isAfter(time);
        }catch (Exception ex){
            return false;
        }
    }

    public String getUsername(String token){
        return this.obterClaims(token).getSubject();
    }
}