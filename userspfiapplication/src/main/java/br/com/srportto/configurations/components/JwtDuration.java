package br.com.srportto.configurations.components;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class JwtDuration {
    private Integer jwtDuration ;

    public JwtDuration(@Value("${jwt.duration}") Integer jwtDuration ){
        this.jwtDuration = jwtDuration ;
    }
}