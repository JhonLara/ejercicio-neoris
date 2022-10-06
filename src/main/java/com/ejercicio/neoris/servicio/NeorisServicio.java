package com.ejercicio.neoris.servicio;

import java.time.LocalDate;
import java.util.List;

import com.ejercicio.neoris.dto.ReporteDTO;
import com.ejercicio.neoris.entidades.Cliente;
import com.ejercicio.neoris.entidades.Cuenta;
import com.ejercicio.neoris.entidades.Movimiento;

public interface NeorisServicio {

	public void crearCuentas(List<Cuenta> cuentas);

	public void eliminarCuenta(Long idCuenta);

	public void crearMovimientos(List<Movimiento> movimientos) throws Exception;

	public void eliminarMovimiento(Long idMovimiento);

	public void crearClientes(List<Cliente> clientes);

	public void eliminarCliente(Long idCliente);

	public List<Cliente> obtenerClientes();

	public List<Cuenta> obtenerCuentas();

	public List<Movimiento> obtenerMovimientos();

	public List<ReporteDTO> obtenerReporte(LocalDate fechaInicio, LocalDate fechaFin, Long idCliente);

}
