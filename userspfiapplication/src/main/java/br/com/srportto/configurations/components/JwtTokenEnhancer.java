package br.com.srportto.configurations.components;

import br.com.srportto.models.entities.User;
import br.com.srportto.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

// Essa classe serve para adicionar informacoes a um token
@Component
@AllArgsConstructor
public class JwtTokenEnhancer implements TokenEnhancer {

	private UserRepository userRepository;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

		User user = userRepository.findByEmail(authentication.getName());
		
		Map<String, Object> map = new HashMap<>();
		map.put("userFirstName", user.getFirstName());
		map.put("userId", user.getId());

		DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken; //exemplo de down casting
		token.setAdditionalInformation(map);
		
		return accessToken;
	}
}
