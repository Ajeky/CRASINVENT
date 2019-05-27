/**
 * 
 */
package com.salesianostriana.damcrasinvent;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.salesianostriana.damcrasinvent.model.Invent;
import com.salesianostriana.damcrasinvent.model.Usuario;
import com.salesianostriana.damcrasinvent.servicios.InventServicio;
import com.salesianostriana.damcrasinvent.servicios.UsuarioServicio;

/**
 * Clase que testea los métodos de la clase InventServicio
 * {@link com.salesianostriana.damcrasinvent.servicios.InventServicio}. Sus
 * atributos son los servicios necesarios para probar los métodos.
 * 
 * @author Álvaro Márquez
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class InventServicioTests {

	@Autowired
	InventServicio invServi;
	@Autowired
	UsuarioServicio userServi;

	/**
	 * Método que comprueba que el buscar inventarios por nombre funcione
	 * correctamente.
	 */
	@Test
	public void test_buscarPorNombre() {
		Invent nuevo = new Invent();
		nuevo.setNombre("Hola");
		invServi.add(nuevo);

		assertEquals(nuevo.getNombre(), invServi.findByNombre("Hola").get(0).getNombre());
	}

	/**
	 * Método que comprueba que el encontrar inventarios a partir del usuario al que
	 * pertenecen funcione correctamente. Por ahora da error, hay que arreglar el
	 * test ya que el método en el proyecto funciona correctamente.
	 */
	@Test
	public void test_encontrarInventariosDeUnUsuario() {
		Usuario nuevo = new Usuario();
		nuevo.setId(0);
		Invent prueba = new Invent();
		prueba.setUsuario(nuevo);
		Invent prueba2 = new Invent();
		prueba2.setUsuario(nuevo);

		userServi.add(nuevo);

		invServi.add(prueba);
		invServi.add(prueba2);

		List<Invent> lista = new ArrayList<Invent>();
		lista.add(prueba);
		lista.add(prueba2);

		assertEquals(lista, invServi.encontrarInventariosDeUnUsuario(0));
	}

}
