/**
 * 
 */
package com.salesianostriana.damcrasinvent.model;

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

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
public class UsuarioEmpresa extends Usuario {
	
	private String CIF;
	private String nombreEmpresa;
	private String telefonoEmpresa;
	private String campoEmpresa;
	private String metodoPago;
	private String direccionFacturacion;


}
