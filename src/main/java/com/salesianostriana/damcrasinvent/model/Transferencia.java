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
public class Transferencia extends MetodosPago {
	
	private String IBAN;
	private String titular;
	/**
	 * @param usuarios
	 * @param iBAN
	 * @param titular
	 */
	public Transferencia(List<UsuarioEmpresa> usuarios, String iBAN, String titular) {
		super(usuarios);
		IBAN = iBAN;
		this.titular = titular;
	}
	
	

}
