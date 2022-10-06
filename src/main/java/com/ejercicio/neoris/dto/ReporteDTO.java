package com.ejercicio.neoris.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class ReporteDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fecha;
	private String nombre;
	private String numeroCuenta;
	private String tipo;
	private Long saldoIncial;
	private Boolean estado;
	private Long movimiento;
	private Long saldoDisponible;

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getSaldoIncial() {
		return saldoIncial;
	}

	public void setSaldoIncial(Long saldoIncial) {
		this.saldoIncial = saldoIncial;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Long getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(Long movimiento) {
		this.movimiento = movimiento;
	}

	public Long getSaldoDisponible() {
		return saldoDisponible;
	}

	public void setSaldoDisponible(Long saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ReporteDTO(String fecha, String nombre, String numeroCuenta, String tipo, Long saldoIncial, Boolean estado,
			Long movimiento, Long saldoDisponible) {
		super();
		this.fecha = fecha;
		this.nombre = nombre;
		this.numeroCuenta = numeroCuenta;
		this.tipo = tipo;
		this.saldoIncial = saldoIncial;
		this.estado = estado;
		this.movimiento = movimiento;
		this.saldoDisponible = saldoDisponible;
	}

}
