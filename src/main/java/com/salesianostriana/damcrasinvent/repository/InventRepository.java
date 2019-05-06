/**
 * 
 */
package com.salesianostriana.damcrasinvent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.damcrasinvent.model.Invent;

/**
 * @author amarquez
 *
 */
public interface InventRepository extends JpaRepository<Invent, Long> {

}
