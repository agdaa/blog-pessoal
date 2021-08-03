package com.generationbr.blogPessoal.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generationbr.blogPessoal.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	List<Usuario> findAllByNomeContaining (String nome);
	Optional<Usuario> findByLoginAndSenha(String usuario, String senha);	
	Optional<Usuario> findByLogin(String userName);
}
