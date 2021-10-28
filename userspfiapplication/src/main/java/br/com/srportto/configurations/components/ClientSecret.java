package br.com.srportto.configurations.components;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ClientSecret {
    private String clientSecret;

    public ClientSecret(@Value("${security.oauth2.client.client-secret}") String clientSecret){
        this.clientSecret = clientSecret;
    }
}