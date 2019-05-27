/**
 * 
 */
package com.salesianostriana.damcrasinvent.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Clase POJO del objeto Usuario. Es clase padre de UsuarioEmpresa
 * {@link com.salesianostriana.damcrasinvent.model.UsuarioEmpresa}, que es algo
 * así como "UsuarioPremium". El atributo booleano admin especifica si el
 * usuario tiene rol de administrador (true) o no (false).
 *
 * @author Álvaro Márquez
 */

@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class Usuario implements UserDetails {

	/**
	 * ID de la entidad usuario en la base de datos. Se autogenera con una
	 * secuencia.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

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

	/**
	 * Lista de inventarios que pertenecen al usuario
	 */
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "usuario")
	private List<Invent> invents;

	public void addInvent(Invent i) {
		this.invents.add(i);
		i.setUsuario(this);
	}

	public void removeInvent(Invent i) {
		this.invents.remove(i);
		i.setUsuario(null);
	}

	/**
	 * Constructor que especifica todos los atributos menos los generados
	 * automáticamente (id, cuentaCaducada, cuentaBloqueada y credencialesCaducadas,
	 * además de la lista de inventarios
	 * 
	 * @param nombre
	 * @param apellidos
	 * @param email
	 * @param nickname
	 * @param password
	 * @param telefono
	 */
	public Usuario(String nombre, String apellidos, String email, String nickname, String password, String telefono,
			boolean admin) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.telefono = telefono;
		this.admin = admin;
	}

	/**
	 * Constructor poco utilizado. En caso de que se requiera especificar el id y la
	 * lista de inventarios, mayormente para realizar pruebas
	 * 
	 * @param id
	 * @param nombre
	 * @param apellidos
	 * @param email
	 * @param nickname
	 * @param password
	 * @param telefono
	 * @param invents
	 */
	public Usuario(long id, String nombre, String apellidos, String email, String nickname, String password,
			String telefono, List<Invent> invents) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.telefono = telefono;
		this.invents = invents;
	}

	/**
	 * Método que otorga rol de administrador o usuario dependiendo de si el
	 * atributo Admin se ha inicializado a true o no
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (admin) {
			return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else {
			return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
		}
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !cuentaCaducada;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !cuentaBloqueada;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !credencialesCaducadas;
	}

	@Override
	public boolean isEnabled() {
		return !cuentaBloqueada;
	}

}
