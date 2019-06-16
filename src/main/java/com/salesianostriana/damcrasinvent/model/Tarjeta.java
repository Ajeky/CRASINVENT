/**
 * 
 */
package com.salesianostriana.damcrasinvent.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase pojo del objeto Tarjeta. Para más información visitar la clase
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
public class Tarjeta extends MetodosPago {

	/**
	 * Número de la tarjeta
	 */
	private String numero;

	/**
	 * Fecha de caducidad de la tarjeta
	 */	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaCad;

	/**
	 * Nombre y apellidos del titular de la tarjeta
	 */
	private String titular;

	/**
	 * Código de seguridad de la tarjeta
	 */
	private String CVV;

	/**
	 * Constructor con todos los atributos de la clase. No debería hacer falta, está
	 * como colchón de seguridad en caso de que hiciera falta
	 */
	public Tarjeta(String numero, LocalDate fechaCad, String titular, String cVV) {
		this.numero = numero;
		this.fechaCad = fechaCad;
		this.titular = titular;
		CVV = cVV;
	}

}
