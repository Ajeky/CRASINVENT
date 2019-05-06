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
public class UsuarioEmpresa extends Usuario {
	
	private String CIF;
	private String nombreEmpresa;
	private String telefonoEmpresa;
	private String campoEmpresa;
	private String metodoPago;
	private String direccionFacturacion;

	/**
	 * 
	 */
	public UsuarioEmpresa() {
		// TODO Auto-generated constructor stub
	}

}
