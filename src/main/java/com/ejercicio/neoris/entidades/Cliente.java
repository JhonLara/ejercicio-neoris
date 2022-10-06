package com.ejercicio.neoris.entidades;

import javax.persistence.Entity;

@Entity
public class Cliente extends Persona {

	private String contrasenia;

	private String estado;

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
