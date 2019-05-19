/**
 * 
 */
package com.salesianostriana.damcrasinvent.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

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
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
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

	/**
	 * @param nombre
	 * @param apellidos
	 * @param email
	 * @param nickname
	 * @param password
	 * @param telefono
	 * @param cIF
	 * @param nombreEmpresa
	 * @param telefonoEmpresa
	 * @param campoEmpresa
	 * @param direccionFacturacion
	 */
	public UsuarioEmpresa(String nombre, String apellidos, String email, String nickname, String password,
			String telefono, String cIF, String nombreEmpresa, String telefonoEmpresa, String campoEmpresa,
			String direccionFacturacion) {
		super(nombre, apellidos, email, nickname, password, telefono);
		CIF = cIF;
		this.nombreEmpresa = nombreEmpresa;
		this.telefonoEmpresa = telefonoEmpresa;
		this.campoEmpresa = campoEmpresa;
		this.direccionFacturacion = direccionFacturacion;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_PREMIUMUSER"));
}

}
