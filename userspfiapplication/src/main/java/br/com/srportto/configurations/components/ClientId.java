package br.com.srportto.configurations.components;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
@Getter
public class ClientId {
    private String clientId;

    public ClientId(@Value("${security.oauth2.client.client-id}") String clientId){
        this.clientId = clientId;
    }
}