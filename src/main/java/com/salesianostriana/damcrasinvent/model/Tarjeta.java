/**
 * 
 */
package com.salesianostriana.damcrasinvent.model;

import java.time.LocalDate;
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
public class Tarjeta extends MetodosPago {
	
	private String numero;
	private LocalDate fechaCad;
	private String titular;
	private String CVV;
	
	/**
	 * @param usuarios
	 * @param numero
	 * @param fechaCad
	 * @param titular
	 * @param cVV
	 */
	public Tarjeta(List<UsuarioEmpresa> usuarios, String numero, LocalDate fechaCad, String titular, String cVV) {
		super(usuarios);
		this.numero = numero;
		this.fechaCad = fechaCad;
		this.titular = titular;
		CVV = cVV;
	}


}
