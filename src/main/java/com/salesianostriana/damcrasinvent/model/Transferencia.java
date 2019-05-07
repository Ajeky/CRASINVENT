/**
 * 
 */
package com.salesianostriana.damcrasinvent.model;

import javax.persistence.Entity;

import lombok.Data;

/**
 * @author amarquez
 *
 */

@Data
@Entity
public class Transferencia extends MetodosPago {
	
	private String IBAN;
	private String titular;

}
