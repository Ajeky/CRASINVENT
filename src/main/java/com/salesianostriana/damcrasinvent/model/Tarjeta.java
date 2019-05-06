/**
 * 
 */
package com.salesianostriana.damcrasinvent.model;

import java.time.LocalDate;

import javax.persistence.Entity;

/**
 * @author amarquez
 *
 */
@Entity
public class Tarjeta extends MetodosPago {
	
	private String numero;
	private LocalDate fechaCad;
	private String titular;
	private String CVV;

	
	/**
	 * 
	 */
	public Tarjeta() {
		// TODO Auto-generated constructor stub
	}

}
