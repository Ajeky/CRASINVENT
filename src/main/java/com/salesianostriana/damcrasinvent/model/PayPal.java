/**
 * 
 */
package com.salesianostriana.damcrasinvent.model;

import java.util.List;

import javax.persistence.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author amarquez
 *
 */

@Getter @Setter @NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
public class PayPal extends MetodosPago {
	
	private String correo;

	/**
	 * @param usuarios
	 * @param correo
	 */
	public PayPal(String correo) {
		this.correo = correo;
	}
	
	

	
}
