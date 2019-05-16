/**
 * 
 */
package com.salesianostriana.damcrasinvent.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

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
	private String direccionFacturacion;
	
	@ManyToMany
	@JoinTable(
			joinColumns = @JoinColumn(name="usuario_id"),
			inverseJoinColumns = @JoinColumn(name="metPago_id")
		)
	private List<MetodosPago> metodosPago = new ArrayList<>();
	
	public void addMetodoPago(MetodosPago m) {
		metodosPago.add(m);
		m.getUsuarios().add(this);
	}
	
	public void deleteMetodoPago(MetodosPago m) {
		metodosPago.remove(m);
		m.getUsuarios().remove(this);
	}
	

}
