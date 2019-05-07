/**
 * 
 */
package com.salesianostriana.damcrasinvent.model;

import java.time.LocalDate;

import javax.persistence.Entity;

import lombok.Data;

/**
 * @author amarquez
 *
 */

@Data
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
