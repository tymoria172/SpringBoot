package br.org.generation.aejames.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.org.generation.aejames.model.Usuario;
import br.org.generation.aejames.repository.UsuarioRepository;


/*	Implementa a interface UserDetails, sendo responsável por recuperar os dados
 *  do usuário no Banco de Dados pelo usuário e converter em um objeto da Classe 
 *  UserDetailsImpl */


@Service // Indica que é uma Service / Serviço
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	/* Obtem os dados de um usuário com um determinado nome de usuário */
	
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		
		Optional<Usuario> usuario = usuarioRepository.findByUsuario(userName);
		
		// Se o Usuário informado não existir, lnaça uma Exception 
		usuario.orElseThrow(() -> new UsernameNotFoundException(userName + "Not found."));
		
		/* Retorna um objeto do tipo UserDetailsImpl(com caracteristicas e direitos) 
		 * criado com os dados recuperados do BD */ 
		return usuario.map(UserDetailsImpl::new).get();
		
	}
	
	
}
