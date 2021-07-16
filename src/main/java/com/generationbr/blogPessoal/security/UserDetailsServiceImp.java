package com.generationbr.blogPessoal.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.generationbr.blogPessoal.models.Usuario;
import com.generationbr.blogPessoal.repositories.UsuarioRepository;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

	@Autowired
	private UsuarioRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<Usuario> user = userRepository.findByLogin(userName);
		user.orElseThrow(() -> new UsernameNotFoundException(userName + " não encontrado."));

		return user.map(UserDetailsImp::new).get();
	}
	
	
	
}
