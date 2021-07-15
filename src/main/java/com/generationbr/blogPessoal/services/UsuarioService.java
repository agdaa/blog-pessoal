package com.generationbr.blogPessoal.services;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.generationbr.blogPessoal.models.Usuario;
import com.generationbr.blogPessoal.models.UsuarioLogin;
import com.generationbr.blogPessoal.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repositoryU;
	
	public Usuario cadastrarUsuario (Usuario usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
		
		return repositoryU.save(usuario);
	
	}

	public Optional<UsuarioLogin> logar (Optional<UsuarioLogin> usuarioParaLogar) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = repositoryU.findByUsuario(usuarioParaLogar.get().getLogin());
		
		if(usuario.isPresent()) {
			//comparando senha encriptada no banco com senha utilizada no login
			if(encoder.matches(usuarioParaLogar.get().getSenha(), usuario.get().getSenha())) {
										
				String auth = usuarioParaLogar.get().getLogin() + ":" + usuarioParaLogar.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);

				usuarioParaLogar.get().setToken(authHeader);				
				usuarioParaLogar.get().setNome(usuario.get().getNome());
				usuarioParaLogar.get().setSenha(usuario.get().getSenha());
				
				return usuarioParaLogar;
			}
		}
		return null;
	}
}
