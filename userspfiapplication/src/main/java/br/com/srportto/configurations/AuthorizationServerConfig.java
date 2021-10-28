package br.com.srportto.configurations;

import br.com.srportto.configurations.components.ClientId;
import br.com.srportto.configurations.components.ClientSecret;
import br.com.srportto.configurations.components.JwtDuration;
import br.com.srportto.configurations.components.JwtTokenEnhancer;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

@Configuration
@EnableAuthorizationServer
@AllArgsConstructor
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	private ClientId clientId;
	private ClientSecret clientSecret;
	private JwtDuration jwtDuration;
	private BCryptPasswordEncoder passwordEncoder;
	private JwtAccessTokenConverter accessTokenConverter;
	private JwtTokenStore tokenStore;
	private AuthenticationManager authenticationManager;
	private JwtTokenEnhancer tokenEnhancer;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security){
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
		.withClient(clientId.getClientId())
		.secret(passwordEncoder.encode(clientSecret.getClientSecret()))
		.scopes("read", "write")
		.authorizedGrantTypes("password")
		.accessTokenValiditySeconds(jwtDuration.getJwtDuration());
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		TokenEnhancerChain chain = new TokenEnhancerChain();
		chain.setTokenEnhancers(Arrays.asList(accessTokenConverter, tokenEnhancer));
		
		endpoints.authenticationManager(authenticationManager)
		.tokenStore(tokenStore)
		.accessTokenConverter(accessTokenConverter)
		.tokenEnhancer(chain);
	}
}
