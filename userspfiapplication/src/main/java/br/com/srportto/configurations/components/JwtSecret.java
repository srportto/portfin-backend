package br.com.srportto.configurations.components;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class JwtSecret {
    private String jwtSecret ;

    public JwtSecret(@Value("${jwt.secret}") String jwtSecret ){
        this.jwtSecret = jwtSecret ;
    }
}