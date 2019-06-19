package com.salesianostriana.damcrasinvent.excepciones;

public class ExcepcionCaducidad extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ExcepcionCaducidad() {
		
	}
	
	public ExcepcionCaducidad(String mensaje) {
		super ("Introduzca una fecha válida, por favor");
	}

}
