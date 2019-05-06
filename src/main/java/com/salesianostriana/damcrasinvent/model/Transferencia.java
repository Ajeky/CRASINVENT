/**
 * 
 */
package com.salesianostriana.damcrasinvent.model;

import javax.persistence.Entity;

/**
 * @author amarquez
 *
 */

@Entity
public class Transferencia extends MetodosPago {
	
	private String IBAN;
	private String titular;

}
