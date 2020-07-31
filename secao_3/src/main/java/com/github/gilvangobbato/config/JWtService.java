package com.github.gilvangobbato.config;

import com.github.gilvangobbato.domain.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
}
