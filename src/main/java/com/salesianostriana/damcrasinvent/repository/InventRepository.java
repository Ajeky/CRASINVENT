package com.salesianostriana.damcrasinvent.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.damcrasinvent.model.Invent;

/**
 * Clase que gestiona las operaciones relacionadas con la clase Invent
 * {@link com.salesianostriana.damcrasinvent.model.Invent}
 * 
 * @author Álvaro Márquez Mata
 *
 */
public interface InventRepository extends JpaRepository<Invent, Long> {

	/**
	 * Método que busca un inventario a partir de su nombre, ignorando las
	 * mayúsculas. De momento no está implementado, por lo que es inservible.
	 * 
	 * @param nombre Nombre del inventario a buscar
	 * @return Lista de usuarios cuyo nombre coincide con la búsqueda.
	 */

	public List<Invent> findByNombreContainingIgnoreCase(String nombre);
	
	public  Page<Invent> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}
