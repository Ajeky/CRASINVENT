package com.salesianostriana.damcrasinvent.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase pojo del objeto HistoricoUsuarios. Genera una tabla en la que se
 * guardan los usuarios{@link com.salesianostriana.damcrasinvent.model.Usuario}
 * que se han dado de baja.
 * 
 * @author Álvaro Márquez
 *
 */
@Entity
@Data
@NoArgsConstructor
public class HistoricoUsuarios {

	/**
	 * Id que identifica a cada usuario.
	 */
	@Id
	long id;

	/**
	 * Nombre real de la persona que utiliza el usuario
	 */
	private String nombre;

	/**
	 * Apellidos reales de la persona que utiliza el usuario
	 */
	private String apellidos;
	@Column(unique = true)

	/**
	 * Email asociado a la cuenta del usuario
	 */
	private String email;
	@Column(unique = true)

	/**
	 * Nickname a utilizar por el usuario
	 */
	private String nickname;

	/**
	 * Contraseña a utilizar por el usuario
	 */
	private String password;

	/**
	 * Telefono real de la persona que utiliza el usuario
	 */
	private String telefono;

	/**
	 * Determina si el usuario tiene privilegios de administrador (true) o no
	 * (false)
	 */
	private boolean admin;

	/**
	 * Determina si la cuenta está caducada
	 */
	private boolean cuentaCaducada;

	/**
	 * Determina si la cuenta ha sido bloqueada
	 */
	private boolean cuentaBloqueada;

	/**
	 * Determina si las credenciales que utiliza la cuenta deben renovarse
	 */
	private boolean credencialesCaducadas;

}
