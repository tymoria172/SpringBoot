package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.example.demo.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@BeforeAll
	void start() {

		// ! = Serve para verificar se o usuario nao existe, se nao existir ele cria e
		// salva em usuario.

		Usuario usuario = new Usuario(0, "João da Silva", "joao@email.com.br", "123456");
		if (!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			usuarioRepository.save(usuario);

		usuario = new Usuario(0, "Manuel da Silva", "manuel@email.com.br", "13465278");
		if (!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			usuarioRepository.save(usuario);

		usuario = new Usuario(0, "Frederico da Silva", "frederico@email.com.br", "13465278");
		if (!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			usuarioRepository.save(usuario);

		usuario = new Usuario(0, "Paulo Antunes", "paulo@email.com.br", "13465278");
		if (!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			usuarioRepository.save(usuario);
	}

	@Test
	@DisplayName("📰 Retorna o Nome")
	public void findByNomeRetornaNome() {

		// Equals compara os parametros, para ver se batem
		// caso o nome seja diferente ele nao armazena no usuario e apresentara o valor
		// de nullo

		Usuario usuario = usuarioRepository.findByNome("João da Silva");
		assertTrue(usuario.getNome().equals("João da Silva"));

	}

	@Test
	@DisplayName("📰 Retorna 3 usuarios")
	public void findAllByNomeContainingIgnoreCaseRetornaTresUsuarios() {

		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");
		assertEquals(3, listaDeUsuarios.size());

		// Assert Equal vai validar se eles sao iguais.
	}

	@AfterAll
	public void end() {
		System.out.println("Teste Finalizado!");

		// END ENCERRA OS TESTES
		/*
		 * O método start(), anotado com a anotação @BeforeAll, inicializa 4 objetos do
		 * tipo Usuario e executa em todos os objetos o método findByUsuario() para
		 * verificar se já existe o usuário antes de criar. No método
		 * findByNomeRetornaNome(), verifica se existe algum usuário cujo nome seja
		 * “João da Silva”, através da assertion AssertTrue(). Se existir, passa no
		 * teste. No método findAllByNomeContainingIgnoreCaseRetornaTresUsuarios(),
		 * verifica se o numero de usuários que contenham no nome a String “Silva” é
		 * igual a 3, através da assertion AssertEquals(). O método size(), que pertence
		 * a Collection List, retorna o tamanho da List. Se o tamanho da List for igual
		 * 3, o teste será aprovado.
		 */

	}
}
