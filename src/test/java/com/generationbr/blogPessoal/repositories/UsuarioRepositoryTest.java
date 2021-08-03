package com.generationbr.blogPessoal.repositories;

import javax.validation.Validator;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.generationbr.blogPessoal.models.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UsuarioRepositoryTest {
	
	public Usuario usuario;
	
	public UsuarioRepository repositoryUsuario;

	@Autowired
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();
	
	@Test
	public void start() {

		usuario = new Usuario();
		if(repositoryUsuario.findByLogin(usuario.getLogin()) != null) {
			repositoryUsuario.save(usuario);
		}

	};


}
