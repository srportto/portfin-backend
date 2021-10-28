package br.com.srportto.configurations;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

@Configuration
@EnableResourceServer
@AllArgsConstructor
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private Environment env;	// ambiente de execução da aplicação
	private JwtTokenStore tokenStore;

	//? rotas publicas
	private static final String[] PUBLIC = { "/oauth/token", "/h2-console/**" };

	//? Rotas liberadas somente para o perfils OPERATOR/ADMIN
	private static final String[] OPERATOR_OR_ADMIN = { "/products/**", "/categories/**" };

	//? Rota liberada somente para o perfil ADMIN
	private static final String[] ADMIN = { "/users/**" };	

	//? Esse metodo validara se o token eh valido para acessar o recurso
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore);
	}

	//? Esse metodo
	@Override
	public void configure(HttpSecurity http) throws Exception {

		//? Caso a lista de profiles ativos contenha o profile "test"
		//? entao deve-se liberar o H2
		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}
		
		http.authorizeRequests()
		.antMatchers(PUBLIC).permitAll()												//> Para acessar os recursos da aplicação declarados no vetor de strings PUBLIC, não exigir nenhum perfil e permitir todos os acessos os tipos de acessos
		.antMatchers(HttpMethod.GET, OPERATOR_OR_ADMIN).permitAll()						//> Para acessar os recursos da aplicação declarados no vetor de strings OPERATOR_OR_ADMIN, permitir todos os acessos somente quando acessadas com GET
		.antMatchers(OPERATOR_OR_ADMIN).hasAnyRole("OPERADOR", "ADMINISTRADOR")	//> Para acessar os recursos da aplicação declarados no vetor de strings OPERATOR_OR_ADMIN, quando acessadas diferentes de GET, exigir  os perfils ADMIN ou OPERATOR
		.antMatchers(ADMIN).hasRole("ADMINISTRADOR")									//> Para acessar os recursos da aplicação declarados no vetor de strings ADMIN, exigir  os perfils ADMIN
		.anyRequest().authenticated();													//> Para acessar qualquer outro recursos (fora os de cima) , só precisa estar autenticado
	}	
}
