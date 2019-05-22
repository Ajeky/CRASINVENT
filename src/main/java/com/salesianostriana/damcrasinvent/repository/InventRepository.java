/**
 * 
 */
package com.salesianostriana.damcrasinvent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.damcrasinvent.model.Invent;

/**
 * @author amarquez
 *
 */
public interface InventRepository extends JpaRepository<Invent, Long> {
	

	public  List<Invent> findByNombreContainingIgnoreCase(String nombre);
}
