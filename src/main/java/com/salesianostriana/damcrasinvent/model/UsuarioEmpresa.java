/**
 * 
 */
package com.salesianostriana.damcrasinvent.model;

import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author amarquez
 *
 */

@Data @NoArgsConstructor
@Entity
public class UsuarioEmpresa extends Usuario {
	
	private String CIF;
	private String nombreEmpresa;
	private String telefonoEmpresa;
	private String campoEmpresa;
	private String metodoPago;
	private String direccionFacturacion;


}
