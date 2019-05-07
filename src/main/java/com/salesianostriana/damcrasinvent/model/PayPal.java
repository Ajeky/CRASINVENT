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
public class PayPal extends MetodosPago {
	
	private String correo;

}
