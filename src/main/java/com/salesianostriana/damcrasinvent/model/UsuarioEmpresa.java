/**
 * 
 */
package com.salesianostriana.damcrasinvent.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
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
 * Clase POJO del objeto UsuarioEmpresa. Extiende a la clase Usuario
 * {@link com.salesianostriana.damcrasinvent.model.Usuario}. Está pensado para
 * que sólo empresas puedan ser usuarios premium, pero no está implementado que
 * tengan ninguna ventaja en el proyecto por falta de tiempo, por lo que por
 * ahora a efectos prácticos es inservible.
 * 
 * @author Álvaro Márquez
 *
 */

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
public class UsuarioEmpresa extends Usuario {

	/**
	 * CIF de la empresa
	 */
	@Column(nullable = false)
	private String CIF;

	/**
	 * Nombre de la empresa
	 */
	private String nombreEmpresa;

	/**
	 * Teléfono profesional de la empresa
	 */
	private String telefonoEmpresa;

	/**
	 * campo profesional al que pertenece la empresa
	 */
	private String campoEmpresa;

	/**
	 * Dirección de facturación de la empresa
	 */
	private String direccionFacturacion;

	/**
	 * Lista de Métodos de
	 * Pago{@link com.salesianostriana.damcrasinvent.model.MetodosPago} que tiene
	 * registrados la empresa. Es una lista en lugar de un objeto individual por si
	 * la empresa quiere tener varios registrados en caso de que uno falle.
	 */
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany
	@JoinTable(joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "metPago_id"))
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
	 * Constructor con todos los atributos de la clase. No debería hacer falta, está
	 * como colchón de seguridad en caso de que hiciera falta
	 */
	public UsuarioEmpresa(String nombre, String apellidos, String email, String nickname, String password,
			String telefono, boolean isAdmin, String cif, String nombreEmpresa, String telefonoEmpresa,
			String campoEmpresa, String direccionFacturacion) {
		super(nombre, apellidos, email, nickname, password, telefono, isAdmin);
		this.CIF = cif;
		this.nombreEmpresa = nombreEmpresa;
		this.telefonoEmpresa = telefonoEmpresa;
		this.campoEmpresa = campoEmpresa;
		this.direccionFacturacion = direccionFacturacion;
	}
	
	public UsuarioEmpresa(String cif, String nombreEmpresa, String telefonoEmpresa, String campoEmpresa, String direccionFacturacion) {
		this.CIF = cif;
		this.nombreEmpresa = nombreEmpresa;
		this.telefonoEmpresa = telefonoEmpresa;
		this.campoEmpresa = campoEmpresa;
		this.direccionFacturacion = direccionFacturacion;
	}

	/**
	 * Método que otorga al usuario el rol de usuario premium
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_PREMIUMUSER"));
	}

}
