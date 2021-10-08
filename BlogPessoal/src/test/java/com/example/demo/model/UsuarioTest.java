package com.example.demo.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // configura para usar uma porta random, nao sendo a 8080
public class UsuarioTest {
	
	
	public Usuario usuario;
	public Usuario usuarioErro = new Usuario(); // instanciando objetos teste
	
	@Autowired
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory(); // Valida minhas anotaçoes na usuario. e captura mensagens de erro.
	
	Validator validator = factory.getValidator(); // instanciando meu validator..
	
	@BeforeEach
	public void start() // indica o inicio do teste
	{
		//Crieu um usuario ja que nao tem banco de dados.
		usuario = new Usuario(0L,"João da silva", "joao@gmail.com", "joao123");
		
	}
	
	@Test//Indica que é um teste
	@DisplayName("✔ Valida atributos não nulos") // Indica titulo da telinha de testes.
	void testValidaAtributos() 
	{
		/*Captura mensagens de erro que nem o postman faz. constraints = ERROs
		 * 
		 * Essas mensagens são armazenadas na Collection do tipo SET 
		 * chamada VIOLATIONS
		 * 
		 * Atravez do metodo assertTrue()
		 * 
		 * verificamos se a Collection violations está vazia
          (violations.isEmpty()).

		 * */
		
		Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuario);
		
		System.out.println(violacao.toString()); // ME MOSTRA NO VIOLAÇÃO OS ERROS
		
		assertTrue(violacao.isEmpty()); // Se houver mensagens de erro no meu Validations ele nao valida e falha o teste, se nao houver ele valida e o teste nao tem erros
	}
	
	@Test
	@DisplayName("✖ Não valida Atributos Nulos")
	void testNaoValidaAtributos() {
		
		Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuarioErro);
		System.out.println(violacao.toString());
		
		assertFalse(violacao.isEmpty()); // AssertFalse estou dizendo que espero um resultado Falso ou seja com erros.
	}
	
}
