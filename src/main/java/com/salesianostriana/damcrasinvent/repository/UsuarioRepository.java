package com.salesianostriana.damcrasinvent.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.damcrasinvent.model.Usuario;

/**
 * Clase que gestiona las operaciones relacionadas con la clase Usuario
 * {@link com.salesianostriana.damcrasinvent.model.Usuario}
 * 
 * @author Álvaro Márquez Mata
 *
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	/**
	 * Método que busca un usuario a partir de su email.
	 * 
	 * @param email Email del usuario a buscar
	 * @return Usuario encontrado
	 */
	public Usuario findFirstByEmail(String email);
	
	
	public List<Usuario> findByEmailContainingIgnoreCase(String nombre);
	
	public Page<Usuario> findByEmailContainingIgnoreCase(String nombre, Pageable pageable);

}
