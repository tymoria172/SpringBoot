package br.org.generation.aejames.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/*
 * 
 * 
 * 		COMPORTAMENTO DO SISTEMA DE SEGURANÇA E CONIFIGURAÇOES DAS PERMISSOES DE USUARIOS
 * 
 * 
 * 
 */
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity // Necessario para a ativar a security
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired // Indica a Security que os usuários que trabalharemos vem do nosso Banco de dados
	private UserDetailsService userDetailsService;

	@Override 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// Define que o login será efetuado através dos usuários criados no Banco de dados
		auth.userDetailsService(userDetailsService);

	}
	
	@Bean 
	public PasswordEncoder passwordEncoder() {
		
		// Permite que essse objeto seja instanciado em qualquer classe
		return new BCryptPasswordEncoder();
	}
	
	// Configurando permissoes de http 
	@Override 
	protected void configure (HttpSecurity http) throws Exception { //Configuraçoes de permissoes e entrada dos usuarios
		http.authorizeRequests()
		.antMatchers("/usuarios/logar").permitAll() // logar nao precisa de autorização
		.antMatchers("/usuarios/cadastrar").permitAll() // nem cadastrar 
		.anyRequest().authenticated() // Tirando isso quem nao tem permissao bloqueia
		.and().httpBasic()	// layout do login e senha no url
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // minha sessao não sera temporaria
		.and().cors() // Cross origins ou seja, O beck em um local eo Front em outro
		.and().csrf().disable(); // desativa sistema ant hackers
		
	}
	
	
		
	
	
	

}
