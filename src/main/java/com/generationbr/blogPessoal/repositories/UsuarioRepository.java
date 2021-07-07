package com.generationbr.blogPessoal.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generationbr.blogPessoal.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByUsuario(String userName);
}
