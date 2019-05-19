/**
 * 
 */
package com.salesianostriana.damcrasinvent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.damcrasinvent.model.Usuario;

/**
 * @author amarquez
 *
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Usuario findFirstByEmail(String email);

}
