package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

/*
 * 
 * O teste da Camada Controller é um pouco diferente dos testes anteriores porquê faremos
    Requisições (http Request) e na sequencia o teste analisará se as Respostas das Requisições (http
    Response) foram as esperadas.

*/

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioControllerTest {

	// Faz um post na tua api com esses dados

	@Autowired
	private TestRestTemplate testRestTemplate; // metodo para usar as requisiçoes e metodos http igual o postman faz

	private Usuario usuario;
	private Usuario usuarioAdmin;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@BeforeAll
	public void Start() {

		usuarioAdmin = new Usuario(0L, "Administrador", "admin@email.com.br", "admin123");

		// caso nao exista no meu repositorio um usuario admin, ele cria um na minha API
		// atravez da requisição POST
		if (!usuarioRepository.findByUsuario(usuarioAdmin.getUsuario()).isPresent()) {

			HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuarioAdmin);
			testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST, request, Usuario.class);

		}

		usuario = new Usuario(0L, "Paulo Antunes", "paulo@email.com.br", "13465278");

	}

	@Test
	@Order(1)
	@DisplayName("✔ Cadastrar Usuario! ")
	public void deveRealizarPostUsuario() {

		/*
		 * transforma os atributos que vc passou via json num objeto da classe usuario.
		 * 
		 * Cria a Requisição HTTP passando 4 parâmetros: ID , NOME , USUARIO, SENHA A
		 * URI: Endereço do endpoint (/usuarios/cadastrar); O Método HTTP: Neste exemplo
		 * o método POST; O ObjetoHttpEntity: Neste exemplo o objeto é da Classe
		 * Usuario; O Tipo de Respostaesperada: Neste exemplo será do tipo Usuario
		 * (Usuario.class).
		 * 
		 * 
		 */
		HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuario);

		ResponseEntity<Usuario> resposta = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST, request,
				Usuario.class);

		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
	}

	@Test
	@Order(2)
	@DisplayName("👍 Listar todos os Usuários!")
	public void deveListarOsUsuarios() {

		/*
		 * 
		 * pega todos meus usuarios atravez do withbasicauth eu estou logando, pois
		 * preciso de autorizacao para usar o metod get na minha http
		 * 
		 * Observe que no Método GET não é necessário criar o Objeto request da Classe
		 * HttpEntity, porquê o GET não envia um Objeto na sua Requisição. Lembre-se que
		 * ao criar uma request do tipo GET no Postman você não passa nenhum parâmetro
		 * além da URL do endpoint. 1. Cria a Requisição HTTP passando 4 parâmetros:
		 *  
		 * A URI: Endereço do endpoint (/usuarios/all); O Método HTTP: Neste exemplo o método GET 
		 * 
		 * O Objeto da requisição: Neste exemplo ele será nulo (null);
		 * 
		 * O Tipo de Resposta esperada: Como o objeto da requisição é nulo, a resposta esperada
		 * será do tipo String (String.class).
		 *
		 * 
		 * 
		 */

		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("admin@email.com.br", "admin123")
				.exchange("/usuarios/all", HttpMethod.GET, null, String.class);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}

}
