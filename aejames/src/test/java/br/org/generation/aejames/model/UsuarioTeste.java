package br.org.generation.aejames.model;

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

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UsuarioTeste {

	public Usuario usuario;
	public Usuario usuarioErro = new Usuario(); // instanciado objeto de teste

	@Autowired
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory(); // captura mensagens de erro e aceita
																					// minhas anotaçoes

	Validator validator = factory.getValidator(); // instanciando meu validator

	@BeforeEach
	public void start() { // indica inicio do teste

		usuario = new Usuario(0L, "andre ricardo", "Andre@email.com.br", "andre123"); // criando usuario ja que nao
																						// temos banco de dados

	}

	@Test
	@DisplayName("✔ Valida atributos nao nulos")
	public void testValidaAtributos() {

		Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuario); // salva mensagens de erro no objeto
																					// violacao.

		System.out.println(violacao.toString()); // me mostra as mensagens de erro se tiver

		assertTrue(violacao.isEmpty()); // meu test valida se o violacao estiver vazia, se a condiçao for verdadeira.

	}

	@Test
	@DisplayName("❌ Não valida atributos nulos")
	public void testNaoValidaAtributos() {

		Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuarioErro);
		System.out.println(violacao.toString());

		assertFalse(violacao.isEmpty());
	}

}
