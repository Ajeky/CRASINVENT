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
 * Clase pojo del objeto PayPal. Para más información visitar la clase
 * MetodosPago {@link com.salesianostriana.damcrasinvent.model.MetodosPago}
 * 
 * @author Álvaro Márquez
 *
 */

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
public class PayPal extends MetodosPago {

	/**
	 * Correo asociado a la cuenta de PayPal
	 */
	private String correo;

	/**
	 * Constructor con el único atributo de la clase. No debería hacer falta, está
	 * como colchón de seguridad.
	 */
	public PayPal(String correo) {
		this.correo = correo;
	}

}
