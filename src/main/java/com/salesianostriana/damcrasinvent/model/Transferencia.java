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
 * Clase pojo del objeto Transferencia. Para más información visitar la clase
 * MetodosPago {@link com.salesianostriana.damcrasinvent.model.MetodosPago}
 * 
 * @author Álvaro Márquez Mata
 *
 */

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
public class Transferencia extends MetodosPago {

	/**
	 * IBAN que identifica la cuenta desde la que se hará la transferencia.
	 */
	private String IBAN;

	/**
	 * Datos de la persona a la cual está asociada la cuenta desde la que se hará la
	 * transferencia
	 */
	private String titular;

	/**
	 * Constructor con todos los atributos de la clase. No debería hacer falta, está
	 * como colchón de seguridad en caso de que hiciera falta
	 */
	public Transferencia(String iBAN, String titular) {
		IBAN = iBAN;
		this.titular = titular;
	}

}
