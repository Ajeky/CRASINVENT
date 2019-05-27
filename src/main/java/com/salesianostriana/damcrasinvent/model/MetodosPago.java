/**
 * 
 */
package com.salesianostriana.damcrasinvent.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Clase pojo del objeto MetodosPago. Idealmente debería haber sido abstracta ya
 * que nunca se ha de instanciar un método de pago sin especificar el tipo, pero
 * debido a que tiene asociaciones era necesario que fuera una entidad en la
 * base de datos. Actualmente no está implementado el carrito, por lo que es
 * inservible.
 * 
 * @author Álvaro Márquez
 *
 */

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class MetodosPago {

	/**
	 * ID que identifica a cada Método de Pago en la base de datos. Se autogenera
	 * con una secuencia
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	/**
	 * Lista de empresas
	 * {@link com.salesianostriana.damcrasinvent.model.UsuarioEmpresa} que utilizan
	 * un método de pago en concreto.
	 */
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "metodosPago")
	private List<UsuarioEmpresa> usuarios = new ArrayList<>();

}
