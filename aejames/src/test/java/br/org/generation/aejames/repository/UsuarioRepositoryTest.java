package br.org.generation.aejames.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.org.generation.aejames.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@BeforeAll
	public void start() {

		Usuario usuario = new Usuario(0, "Andre ricardo", "andre@email.com.br", "Andre123");
		if (!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			usuarioRepository.save(usuario);

		usuario = new Usuario(0, "silva ricardo", "silva@email.com.br", "silva123");
		if (!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			usuarioRepository.save(usuario);

		usuario = new Usuario(0, "lorena ricardo", "lorena@email.com.br", "lorena123");
		if (!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			usuarioRepository.save(usuario);

		usuario = new Usuario(0, "laura ricardo", "laura@email.com.br", "laura123");
		if (!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			usuarioRepository.save(usuario);
	}

	@Test
	@DisplayName("🚹 Retorna o nome")
	public void findByNomeRetornaNome() {

		Usuario usuario = usuarioRepository.findByNome("Andre Ricardo");
		assertTrue(usuario.getNome().equals("Andre ricardo"));

	}

	@Test
	@DisplayName("💬 Retorna 3 Usuarios")
	public void findAllByNomeContainingIgnoreCaseRetornaTresUsuarios() {

		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Ricardo");

		assertEquals(4, listaDeUsuarios.size());
	}

	public void end() {

		System.out.println("Teste Finalizado");
	}

}
